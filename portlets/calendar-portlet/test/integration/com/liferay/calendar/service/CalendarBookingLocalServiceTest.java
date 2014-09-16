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

package com.liferay.calendar.service;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.util.test.CalendarBookingTestUtil;
import com.liferay.calendar.util.test.CalendarTestUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.util.test.UserTestUtil;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Miguel Pastor
 */
@RunWith(Arquillian.class)
public class CalendarBookingLocalServiceTest {

	@Test
	public void testGetCalendarsCount() throws Exception {
		User invitingUser = UserTestUtil.addUser();
		User invitedUser = UserTestUtil.addUser();
		Calendar invitingCalendar = CalendarTestUtil.addUserCalendar(
			invitingUser);
		Calendar invitedCalendar = CalendarTestUtil.addUserCalendar(
			invitedUser);

		CalendarBooking calendarBooking =
			CalendarBookingTestUtil.addCalendarBooking(invitingCalendar);

		CalendarBooking childCalendarBooking = 
			CalendarBookingTestUtil.inviteCalendar(
				calendarBooking, invitedCalendar);

		
	}

}