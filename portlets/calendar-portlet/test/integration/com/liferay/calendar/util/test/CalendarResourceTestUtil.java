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

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.util.CalendarResourceUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.test.ServiceContextTestUtil;
import com.liferay.portal.util.test.UserTestUtil;

/**
 * @author Adam Brandizzi
 */

public class CalendarResourceTestUtil {

	public static CalendarResource addUserCalendarResource() throws Exception {
		return addUserCalendarResource(UserTestUtil.addUser());
	}

	public static CalendarResource addUserCalendarResource(User user)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(user.getGroupId());

		return CalendarResourceUtil.getUserCalendarResource(
			user.getUserId(), serviceContext);
	}

}