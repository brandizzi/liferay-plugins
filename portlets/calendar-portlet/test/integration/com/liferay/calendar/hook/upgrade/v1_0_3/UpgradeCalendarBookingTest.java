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

package com.liferay.calendar.hook.upgrade.v1_0_3;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.model.impl.CalendarBookingImpl;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.util.CalendarResourceUtil;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adam Brandizzi
 */
@RunWith(Arquillian.class)
public class UpgradeCalendarBookingTest {

	@Before
	public void setUp() throws Exception {
		_user = UserTestUtil.addUser();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(_user.getCompanyId());

		CalendarResource calendarResource =
			CalendarResourceUtil.getUserCalendarResource(
				_user.getUserId(), serviceContext);

		Calendar calendar = calendarResource.getDefaultCalendar();

		Calendar invitedCalendar = CalendarLocalServiceUtil.addCalendar(
			_user.getUserId(), _user.getGroupId(),
			calendarResource.getCalendarResourceId(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			calendarResource.getTimeZoneId(), RandomTestUtil.randomInt(), false,
			false, false, serviceContext);

		long startDate = DateUtil.newTime();

		_calendarBooking = CalendarBookingLocalServiceUtil.addCalendarBooking(
			_user.getUserId(), calendar.getCalendarId(),
			new long[] { invitedCalendar.getCalendarId() },
			CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomString(), startDate, startDate + 36000000,
			false, null, 0, null, 0, null, serviceContext);

		List<CalendarBooking> childCalendarBookings =
			_calendarBooking.getChildCalendarBookings();
		_childCalendarBooking = childCalendarBookings.get(0);

		if (_childCalendarBooking.isMasterBooking()) {
			_childCalendarBooking = childCalendarBookings.get(1);
		}
	}

	@After
	public void tearDown() throws Exception {
		UserLocalServiceUtil.deleteUser(_user);
	}

	@Test
	public void testApprovedChildOfApprovedEventStaysApproved()
		throws Exception {

		setCalendarBookingsInitialStatuses(
			CalendarBookingWorkflowConstants.STATUS_APPROVED,
			CalendarBookingWorkflowConstants.STATUS_APPROVED);

		UpgradeCalendarBooking upgradeCalendarBooking =
			new UpgradeCalendarBooking();

		upgradeCalendarBooking.doUpgrade();

		EntityCacheUtil.clearCache(CalendarBookingImpl.class);

		_calendarBooking = CalendarBookingLocalServiceUtil.fetchCalendarBooking(
			_calendarBooking.getCalendarBookingId());
		_childCalendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(
				_childCalendarBooking.getCalendarBookingId());

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_APPROVED,
			_calendarBooking.getStatus());

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_APPROVED,
			_childCalendarBooking.getStatus());
	}

	@Test
	public void testApprovedChildOfPendingEventShouldBeMasterPending()
		throws Exception {

		setCalendarBookingsInitialStatuses(
			CalendarBookingWorkflowConstants.STATUS_PENDING,
			CalendarBookingWorkflowConstants.STATUS_APPROVED);

		UpgradeCalendarBooking upgradeCalendarBooking =
			new UpgradeCalendarBooking();

		upgradeCalendarBooking.doUpgrade();

		EntityCacheUtil.clearCache(CalendarBookingImpl.class);

		_calendarBooking = CalendarBookingLocalServiceUtil.fetchCalendarBooking(
			_calendarBooking.getCalendarBookingId());
		_childCalendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(
				_childCalendarBooking.getCalendarBookingId());

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_PENDING,
			_calendarBooking.getStatus());

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_MASTER_PENDING,
			_childCalendarBooking.getStatus());
	}

	@Test
	public void testMaybeChildOfApprovedEventStaysMaybe() throws Exception {
		setCalendarBookingsInitialStatuses(
			CalendarBookingWorkflowConstants.STATUS_APPROVED,
			CalendarBookingWorkflowConstants.STATUS_MAYBE);

		UpgradeCalendarBooking upgradeCalendarBooking =
			new UpgradeCalendarBooking();

		upgradeCalendarBooking.doUpgrade();

		EntityCacheUtil.clearCache(CalendarBookingImpl.class);

		_calendarBooking = CalendarBookingLocalServiceUtil.fetchCalendarBooking(
			_calendarBooking.getCalendarBookingId());
		_childCalendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(
				_childCalendarBooking.getCalendarBookingId());

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_APPROVED,
			_calendarBooking.getStatus());

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_MAYBE,
			_childCalendarBooking.getStatus());
	}

	@Test
	public void testMaybeChildOfPendingEventShouldBeMasterPending()
		throws Exception {

		setCalendarBookingsInitialStatuses(
			CalendarBookingWorkflowConstants.STATUS_PENDING,
			CalendarBookingWorkflowConstants.STATUS_MAYBE);

		UpgradeCalendarBooking upgradeCalendarBooking =
			new UpgradeCalendarBooking();

		upgradeCalendarBooking.doUpgrade();

		EntityCacheUtil.clearCache(CalendarBookingImpl.class);

		_calendarBooking = CalendarBookingLocalServiceUtil.fetchCalendarBooking(
			_calendarBooking.getCalendarBookingId());
		_childCalendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(
				_childCalendarBooking.getCalendarBookingId());

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_PENDING,
			_calendarBooking.getStatus());

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_MASTER_PENDING,
			_childCalendarBooking.getStatus());
	}

	@Test
	public void testPendingChildOfPendingEventShouldBeMasterPending()
		throws Exception {

		setCalendarBookingsInitialStatuses(
			CalendarBookingWorkflowConstants.STATUS_PENDING,
			CalendarBookingWorkflowConstants.STATUS_PENDING);

		UpgradeCalendarBooking upgradeCalendarBooking =
			new UpgradeCalendarBooking();

		upgradeCalendarBooking.doUpgrade();

		EntityCacheUtil.clearCache(CalendarBookingImpl.class);

		_calendarBooking = CalendarBookingLocalServiceUtil.fetchCalendarBooking(
			_calendarBooking.getCalendarBookingId());
		_childCalendarBooking =
			CalendarBookingLocalServiceUtil.fetchCalendarBooking(
				_childCalendarBooking.getCalendarBookingId());

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_PENDING,
			_calendarBooking.getStatus());

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_MASTER_PENDING,
			_childCalendarBooking.getStatus());
	}

	protected void setCalendarBookingsInitialStatuses(
			int masterCalendarBookingStatus, int childCalendarBookingStatus)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		_calendarBooking = CalendarBookingLocalServiceUtil.updateStatus(
			_user.getUserId(), _calendarBooking, masterCalendarBookingStatus,
			serviceContext);

		_childCalendarBooking = CalendarBookingLocalServiceUtil.updateStatus(
			_user.getUserId(), _childCalendarBooking,
			childCalendarBookingStatus, serviceContext);

		Assert.assertEquals(
			masterCalendarBookingStatus, _calendarBooking.getStatus());

		Assert.assertEquals(
			childCalendarBookingStatus, _childCalendarBooking.getStatus());
	}

	private CalendarBooking _calendarBooking;
	private CalendarBooking _childCalendarBooking;
	private User _user;

}