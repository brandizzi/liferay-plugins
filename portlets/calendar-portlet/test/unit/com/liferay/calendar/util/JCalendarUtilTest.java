/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.util.CalendarFactoryImpl;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Adam Brandizzi
 */
public class JCalendarUtilTest {

	@BeforeClass
	public static void setUpClass() {
		new CalendarFactoryUtil().setCalendarFactory(new CalendarFactoryImpl());
	}

	@Test
	public void testGetTimeZoneOffsetLosAngelesDST() {
		Calendar date1 = JCalendarUtil.getJCalendar(
			2012, Calendar.MAY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);
		Calendar date2 = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 2, 12, 0, 0, 0, TimeZoneUtil.GMT);

		long jump = JCalendarUtil.getTimeZoneOffsetBetweenDates(
			date1, date2, _losAngelesTimeZone);

		Assert.assertEquals(0, jump);
	}

	@Test
	public void testGetTimeZoneOffsetLosAngelesDST2NoDST() {
		Calendar date1 = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);
		Calendar date2 = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		long jump = JCalendarUtil.getTimeZoneOffsetBetweenDates(
			date1, date2, _losAngelesTimeZone);

		Assert.assertEquals(-1*(JCalendarUtil.HOUR), jump);
	}

	@Test
	public void testGetTimeZoneOffsetLosAngelesNoDST() {
		Calendar date1 = JCalendarUtil.getJCalendar(
			2013, Calendar.DECEMBER, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);
		Calendar date2 = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 2, 12, 0, 0, 0, TimeZoneUtil.GMT);

		long jump = JCalendarUtil.getTimeZoneOffsetBetweenDates(
			date1, date2, _losAngelesTimeZone);

		Assert.assertEquals(0, jump);
	}

	@Test
	public void testGetTimeZoneOffsetLosAngelesNoDST2DST() {
		Calendar date1 = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);
		Calendar date2 = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		long jump = JCalendarUtil.getTimeZoneOffsetBetweenDates(
			date1, date2, _saoPauloTimeZone);

		Assert.assertEquals(JCalendarUtil.HOUR, jump);
	}

	private static final TimeZone _losAngelesTimeZone = TimeZone.getTimeZone(
		"America/Los_Angeles");
	private static final TimeZone _saoPauloTimeZone = TimeZone.getTimeZone(
		"America/Sao_Paulo");

}