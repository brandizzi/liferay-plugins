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
	public void testLaDstSimulatingTime() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar tzSimulatingDate = JCalendarUtil.getTZSimulatingTime(
			dateToDisplay, LOS_ANGELES_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, tzSimulatingDate.getTimeZone());
		Assert.assertEquals(5, tzSimulatingDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, tzSimulatingDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, tzSimulatingDate.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JULY, tzSimulatingDate.get(Calendar.MONTH));
		Assert.assertEquals(1, tzSimulatingDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testLaDstUtcSimulatingTime() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 5, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar tzSimulatingDate = JCalendarUtil.getTZRealTime(
			dateToDisplay, LOS_ANGELES_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, tzSimulatingDate.getTimeZone());
		Assert.assertEquals(12, tzSimulatingDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, tzSimulatingDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, tzSimulatingDate.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JULY, tzSimulatingDate.get(Calendar.MONTH));
		Assert.assertEquals(1, tzSimulatingDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testLaNoDstSimulatingTime() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar tzSimulatingDate = JCalendarUtil.getTZSimulatingTime(
			dateToDisplay, LOS_ANGELES_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, tzSimulatingDate.getTimeZone());
		Assert.assertEquals(4, tzSimulatingDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, tzSimulatingDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, tzSimulatingDate.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JANUARY, tzSimulatingDate.get(Calendar.MONTH));
		Assert.assertEquals(1, tzSimulatingDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testLaNoDstUtcSimulatingTime() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 4, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar tzSimulatingDate = JCalendarUtil.getTZRealTime(
			dateToDisplay, LOS_ANGELES_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, tzSimulatingDate.getTimeZone());
		Assert.assertEquals(12, tzSimulatingDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, tzSimulatingDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, tzSimulatingDate.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JANUARY, tzSimulatingDate.get(Calendar.MONTH));
		Assert.assertEquals(1, tzSimulatingDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSpDstSimulatingTime() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar tzSimulatingDate = JCalendarUtil.getTZSimulatingTime(
			dateToDisplay, SAO_PAULO_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, tzSimulatingDate.getTimeZone());
		Assert.assertEquals(9, tzSimulatingDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, tzSimulatingDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, tzSimulatingDate.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JULY, tzSimulatingDate.get(Calendar.MONTH));
		Assert.assertEquals(1, tzSimulatingDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSpDstUtcSimulatingTime() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 9, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar tzSimulatingDate = JCalendarUtil.getTZRealTime(
			dateToDisplay, SAO_PAULO_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, tzSimulatingDate.getTimeZone());
		Assert.assertEquals(12, tzSimulatingDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, tzSimulatingDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, tzSimulatingDate.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JULY, tzSimulatingDate.get(Calendar.MONTH));
		Assert.assertEquals(1, tzSimulatingDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSpNoDstSimulatingTime() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar tzSimulatingDate = JCalendarUtil.getTZSimulatingTime(
			dateToDisplay, SAO_PAULO_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, tzSimulatingDate.getTimeZone());
		Assert.assertEquals(10, tzSimulatingDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, tzSimulatingDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, tzSimulatingDate.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JANUARY, tzSimulatingDate.get(Calendar.MONTH));
		Assert.assertEquals(1, tzSimulatingDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSpNoDstUtcSimulatingTime() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 10, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar tzSimulatingDate = JCalendarUtil.getTZRealTime(
			dateToDisplay, SAO_PAULO_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, tzSimulatingDate.getTimeZone());
		Assert.assertEquals(12, tzSimulatingDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, tzSimulatingDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, tzSimulatingDate.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JANUARY, tzSimulatingDate.get(Calendar.MONTH));
		Assert.assertEquals(1, tzSimulatingDate.get(Calendar.DAY_OF_MONTH));
	}

	private static final TimeZone LOS_ANGELES_TIME_ZONE = TimeZone.getTimeZone(
		"America/Los_Angeles");

	private static final TimeZone SAO_PAULO_TIME_ZONE = TimeZone.getTimeZone(
			"America/Sao_Paulo");

}