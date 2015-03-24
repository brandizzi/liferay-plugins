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

import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Adam Brandizzi
 */
public class UpgradeCalendarBooking extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(6);

			sb.append("select CB.calendarBookingId from CalendarBooking CB ");
			sb.append("inner join CalendarBooking PCB on CB.");
			sb.append("parentCalendarBookingId = PCB.calendarBookingId where ");
			sb.append("CB.parentCalendarBookingId <> CB.calendarBookingId ");
			sb.append("and PCB.status = ");
			sb.append(CalendarBookingWorkflowConstants.STATUS_PENDING);

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long calendarBookingId = rs.getLong(1);
				runSQL(
					"update CalendarBooking set status = " +
					CalendarBookingWorkflowConstants.STATUS_MASTER_PENDING +
					" where calendarBookingId = " + calendarBookingId);
			}
		}
		finally {
			DataAccess.cleanUp(con);
		}
	}

}