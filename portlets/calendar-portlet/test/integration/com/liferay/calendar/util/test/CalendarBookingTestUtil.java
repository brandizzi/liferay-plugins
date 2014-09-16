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

package com.liferay.calendar.util.test;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.test.RandomTestUtil;
import com.liferay.portal.util.test.ServiceContextTestUtil;

/**
 * @author Adam Brandizzi
 */

public class CalendarBookingTestUtil {

	public static CalendarBooking addCalendarBooking(Calendar calendar)
		throws Exception {

		User user = UserLocalServiceUtil.getUser(calendar.getUserId());
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(user.getGroupId());

		long startTime = RandomTestUtil.randomLong();
		long endTime = startTime + RandomTestUtil.randomLong();

		return CalendarBookingLocalServiceUtil.addCalendarBooking(
			user.getUserId(), calendar.getCalendarId(), new long[0],
			CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomString(), startTime, endTime,
			RandomTestUtil.randomBoolean(), StringPool.BLANK,
			RandomTestUtil.randomLong(), "email", RandomTestUtil.randomLong(),
			"email", serviceContext);
	}

	public static CalendarBooking inviteCalendar(
		CalendarBooking calendarBooking, Calendar calendar)
	throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				calendarBooking.getGroupId());

		calendarBooking = CalendarBookingLocalServiceUtil.updateCalendarBooking(
			calendarBooking.getUserId(), calendarBooking.getCalendarBookingId(),
			calendarBooking.getCalendarId(),
			new long[] {calendar.getCalendarId()},
			calendarBooking.getTitleMap(), calendarBooking.getDescriptionMap(),
			calendarBooking.getLocation(), calendarBooking.getStartTime(),
			calendarBooking.getEndTime(), calendarBooking.isAllDay(),
			calendarBooking.getRecurrence(), calendarBooking.getFirstReminder(),
			calendarBooking.getFirstReminderType(),
			calendarBooking.getSecondReminder(),
			calendarBooking.getSecondReminderType(),
			calendarBooking.getStatus(), serviceContext);

		return CalendarBookingLocalServiceUtil.getCalendarBooking(
			calendar.getCalendarId(), calendarBooking.getCalendarBookingId());
	}

}