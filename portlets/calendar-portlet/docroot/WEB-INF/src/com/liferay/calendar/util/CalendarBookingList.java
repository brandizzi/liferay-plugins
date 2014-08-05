/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.calendar.util;

import com.google.ical.iter.RecurrenceIterator;
import com.google.ical.iter.RecurrenceIteratorFactory;
import com.google.ical.util.TimeUtils;
import com.google.ical.values.DateValue;
import com.google.ical.values.DateValueImpl;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;

import java.text.ParseException;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Adam Brandizzi
 */
public class CalendarBookingList extends AbstractList<CalendarBooking> {

	public CalendarBookingList(CalendarBooking calendarBooking)
		throws ParseException {

		_calendarBooking = calendarBooking;
		_dateValues = new ArrayList<DateValue>();
		_recurrenceIterator =
			RecurrenceIteratorFactory.createRecurrenceIterator(
				calendarBooking.getRecurrence(),
				_toDateValue(calendarBooking.getStartTime()),
				TimeUtils.utcTimezone());
	}

	@Override
	public CalendarBooking get(int index) {
		DateValue dateValue = _getDateValue(index);

		CalendarBooking newCalendarBooking =
			(CalendarBooking)_calendarBooking.clone();

		Calendar jCalendar = _getStartTimeJCalendar(dateValue);

		newCalendarBooking.setEndTime(
			jCalendar.getTimeInMillis() +_calendarBooking.getDuration());
		newCalendarBooking.setInstanceIndex(index);
		newCalendarBooking.setStartTime(jCalendar.getTimeInMillis());

		return newCalendarBooking;
	}

	@Override
	public int size() {
		return -1;
	}

	private DateValue _getDateValue(int index) {
		for (int i = _dateValues.size(); i < index; i++) {
			if (!_recurrenceIterator.hasNext()) {
				return null;
			}

			_dateValues.add(_recurrenceIterator.next());
		}

		return _dateValues.get(index);
	}

	private Calendar _getStartTimeJCalendar(DateValue dateValue) {
		Calendar jCalendar = JCalendarUtil.getJCalendar(
			_calendarBooking.getStartTime());

		Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(
			dateValue.year(), dateValue.month() - 1, dateValue.day(),
			jCalendar.get(Calendar.HOUR_OF_DAY), jCalendar.get(Calendar.MINUTE),
			jCalendar.get(Calendar.SECOND), jCalendar.get(Calendar.MILLISECOND),
			TimeZone.getTimeZone(StringPool.UTC));

		TimeZone timeZone = _getTimeZone(_calendarBooking);

		int shift = JCalendarUtil.getDSTShift(
			jCalendar, startTimeJCalendar, timeZone);

		startTimeJCalendar.add(Calendar.MILLISECOND, shift);

		return startTimeJCalendar;
	}

	private TimeZone _getTimeZone(CalendarBooking calendarBooking) {
		try {
			return calendarBooking.getTimeZone();
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return TimeZoneUtil.getDefault();
	}

	private DateValue _toDateValue(long time) {
		Calendar jCalendar = JCalendarUtil.getJCalendar(time);

		return new DateValueImpl(
			jCalendar.get(Calendar.YEAR), jCalendar.get(Calendar.MONTH) + 1,
			jCalendar.get(Calendar.DAY_OF_MONTH));
	}

	private static Log _log = LogFactoryUtil.getLog(CalendarBookingList.class);

	private CalendarBooking _calendarBooking;
	private List<DateValue> _dateValues;
	private RecurrenceIterator _recurrenceIterator;

}