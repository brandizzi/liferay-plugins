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
import com.liferay.calendar.model.CalendarBookingConstants;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.util.CalendarResourceUtil;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
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
public class CalendarBookingLocalServiceTest {

	@Before
	public void setUp() throws Exception {
		_user = UserTestUtil.addUser();
	}

	@After
	public void tearDown() throws Exception {
		UserLocalServiceUtil.deleteUser(_user);
	}

	@Test
	public void testAddCalendarBookingWorkflowActionPublish()
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(_user.getCompanyId());

		CalendarResource calendarResource =
			CalendarResourceUtil.getUserCalendarResource(
				_user.getUserId(), serviceContext);
		Calendar calendar = calendarResource.getDefaultCalendar();
		long startDate = DateUtil.newTime();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.addCalendarBooking(
				_user.getUserId(), calendar.getCalendarId(), new long[0],
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomString(), startDate, startDate + 36000000,
				false, null, 0, null, 0, null, serviceContext);

		Assert.assertEquals(
			WorkflowConstants.STATUS_APPROVED, calendarBooking.getStatus());
	}

	@Test
	public void testAddCalendarBookingWorkflowActionSaveDraft()
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(_user.getCompanyId());

		CalendarResource calendarResource =
			CalendarResourceUtil.getUserCalendarResource(
				_user.getUserId(), serviceContext);
		Calendar calendar = calendarResource.getDefaultCalendar();
		long startDate = DateUtil.newTime();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.addCalendarBooking(
				_user.getUserId(), calendar.getCalendarId(), new long[0],
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomString(), startDate, startDate + 36000000,
				false, null, 0, null, 0, null, serviceContext);

		Assert.assertEquals(
			WorkflowConstants.STATUS_DRAFT, calendarBooking.getStatus());
	}

	@Test
	public void testMovedToTrashEventResultsInMovedToTrashChildren()
		throws PortalException {

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

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.addCalendarBooking(
				_user.getUserId(), calendar.getCalendarId(),
				new long[] { invitedCalendar.getCalendarId() },
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomString(), startDate, startDate + 36000000,
				false, null, 0, null, 0, null, serviceContext);

		CalendarBooking childCalendarBooking = getChildCalendarBooking(
			calendarBooking);

		Assert.assertEquals(
			WorkflowConstants.STATUS_PENDING, childCalendarBooking.getStatus());

		CalendarBookingLocalServiceUtil.moveCalendarBookingToTrash(
			_user.getUserId(), calendarBooking);

		childCalendarBooking = getChildCalendarBooking(calendarBooking);

		Assert.assertEquals(
			WorkflowConstants.STATUS_IN_TRASH,
			childCalendarBooking.getStatus());
	}

	@Test
	public void testRestoredFromTrashEventResultsInRestoredFromTrashChildren()
		throws PortalException {

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

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.addCalendarBooking(
				_user.getUserId(), calendar.getCalendarId(),
				new long[] { invitedCalendar.getCalendarId() },
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomString(), startDate, startDate + 36000000,
				false, null, 0, null, 0, null, serviceContext);

		CalendarBooking childCalendarBooking = getChildCalendarBooking(
			calendarBooking);

		Assert.assertEquals(
			WorkflowConstants.STATUS_PENDING, childCalendarBooking.getStatus());

		CalendarBookingLocalServiceUtil.moveCalendarBookingToTrash(
			_user.getUserId(), calendarBooking);

		childCalendarBooking = getChildCalendarBooking(calendarBooking);

		Assert.assertEquals(
			WorkflowConstants.STATUS_IN_TRASH,
			childCalendarBooking.getStatus());

		CalendarBookingLocalServiceUtil.restoreCalendarBookingFromTrash(
			_user.getUserId(), calendarBooking.getCalendarBookingId());

		childCalendarBooking = getChildCalendarBooking(calendarBooking);

		Assert.assertEquals(
			WorkflowConstants.STATUS_PENDING, childCalendarBooking.getStatus());
	}

	@Test
	public void testUpdateCalendarBookingWorkflowActionPublish()
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(_user.getCompanyId());

		CalendarResource calendarResource =
			CalendarResourceUtil.getUserCalendarResource(
				_user.getUserId(), serviceContext);
		Calendar calendar = calendarResource.getDefaultCalendar();
		long startDate = DateUtil.newTime();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.addCalendarBooking(
				_user.getUserId(), calendar.getCalendarId(), new long[0],
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomString(), startDate, startDate + 36000000,
				false, null, 0, null, 0, null, serviceContext);

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		calendarBooking = CalendarBookingLocalServiceUtil.updateCalendarBooking(
			_user.getUserId(), calendarBooking.getCalendarBookingId(),
			calendar.getCalendarId(), new long[0],
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomString(), startDate, startDate + 36000000,
			false, null, 0, null, 0, null, RandomTestUtil.randomInt(),
			serviceContext);

		Assert.assertEquals(
			WorkflowConstants.STATUS_APPROVED, calendarBooking.getStatus());
	}

	@Test
	public void testUpdateCalendarBookingWorkflowActionSaveDraft()
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(_user.getCompanyId());

		CalendarResource calendarResource =
			CalendarResourceUtil.getUserCalendarResource(
				_user.getUserId(), serviceContext);
		Calendar calendar = calendarResource.getDefaultCalendar();
		long startDate = DateUtil.newTime();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.addCalendarBooking(
				_user.getUserId(), calendar.getCalendarId(), new long[0],
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomString(), startDate, startDate + 36000000,
				false, null, 0, null, 0, null, serviceContext);

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		calendarBooking = CalendarBookingLocalServiceUtil.updateCalendarBooking(
			_user.getUserId(), calendarBooking.getCalendarBookingId(),
			calendar.getCalendarId(), new long[0],
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomString(), startDate, startDate + 36000000,
			false, null, 0, null, 0, null, RandomTestUtil.randomInt(),
			serviceContext);

		Assert.assertEquals(
			WorkflowConstants.STATUS_DRAFT, calendarBooking.getStatus());
	}

	@Test
	public void testUpdateCalendarBookingWorkflowActionSaveDraftAfterPublish()
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(_user.getCompanyId());

		CalendarResource calendarResource =
			CalendarResourceUtil.getUserCalendarResource(
				_user.getUserId(), serviceContext);
		Calendar calendar = calendarResource.getDefaultCalendar();

		long startDate = DateUtil.newTime();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.addCalendarBooking(
				_user.getUserId(), calendar.getCalendarId(), new long[0],
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomString(), startDate, startDate + 36000000,
				false, null, 0, null, 0, null, serviceContext);

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		calendarBooking = CalendarBookingLocalServiceUtil.updateCalendarBooking(
			_user.getUserId(), calendarBooking.getCalendarBookingId(),
			calendar.getCalendarId(), new long[0],
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomString(), startDate, startDate + 36000000,
			false, null, 0, null, 0, null, RandomTestUtil.randomInt(),
			serviceContext);

		Assert.assertEquals(
			WorkflowConstants.STATUS_DRAFT, calendarBooking.getStatus());
	}

	@Test
	public void testInviteToDraftEventResultsInMasterPendingChild()
		throws PortalException {

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

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.addCalendarBooking(
				_user.getUserId(), calendar.getCalendarId(),
				new long[] { invitedCalendar.getCalendarId() },
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomString(), startDate, startDate + 36000000,
				false, null, 0, null, 0, null, serviceContext);

		CalendarBooking childCalendarBooking = getChildCalendarBooking(
			calendarBooking);

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_MASTER_PENDING,
			childCalendarBooking.getStatus());
	}

	@Test
	public void testInviteToPublishedEventResultsInPendingChild()
		throws PortalException {

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

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.addCalendarBooking(
				_user.getUserId(), calendar.getCalendarId(),
				new long[] { invitedCalendar.getCalendarId() },
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomString(), startDate, startDate + 36000000,
				false, null, 0, null, 0, null, serviceContext);

		CalendarBooking childCalendarBooking = getChildCalendarBooking(
			calendarBooking);

		Assert.assertEquals(
			WorkflowConstants.STATUS_PENDING, childCalendarBooking.getStatus());
	}

	@Test
	public void testPublishDraftEventResultsInPendingChild()
		throws PortalException {

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

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.addCalendarBooking(
				_user.getUserId(), calendar.getCalendarId(),
				new long[] { invitedCalendar.getCalendarId() },
				CalendarBookingConstants.PARENT_CALENDAR_BOOKING_ID_DEFAULT,
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomString(), startDate, startDate + 36000000,
				false, null, 0, null, 0, null, serviceContext);

		CalendarBooking childCalendarBooking = getChildCalendarBooking(
			calendarBooking);

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_MASTER_PENDING,
			childCalendarBooking.getStatus());

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		calendarBooking = CalendarBookingLocalServiceUtil.updateCalendarBooking(
			_user.getUserId(), calendarBooking.getCalendarBookingId(),
			calendar.getCalendarId(),
			new long[] { invitedCalendar.getCalendarId() },
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomString(), startDate, startDate + 36000000,
			false, null, 0, null, 0, null, RandomTestUtil.randomInt(),
			serviceContext);

		childCalendarBooking = getChildCalendarBooking(calendarBooking);

		Assert.assertEquals(
			CalendarBookingWorkflowConstants.STATUS_PENDING,
			childCalendarBooking.getStatus());
	}

	protected CalendarBooking getChildCalendarBooking(
		CalendarBooking calendarBooking) {

		List<CalendarBooking> childCalendarBookings =
			calendarBooking.getChildCalendarBookings();
		CalendarBooking childCalendarBooking = childCalendarBookings.get(0);

		if (childCalendarBooking.isMasterBooking()) {
			childCalendarBooking = childCalendarBookings.get(1);
		}

		return childCalendarBooking;
	}

	private User _user;

}