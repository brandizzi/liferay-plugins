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

package com.liferay.calendar.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CalendarNotificationTemplate}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarNotificationTemplate
 * @generated
 */
public class CalendarNotificationTemplateWrapper
	implements CalendarNotificationTemplate,
		ModelWrapper<CalendarNotificationTemplate> {
	public CalendarNotificationTemplateWrapper(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		_calendarNotificationTemplate = calendarNotificationTemplate;
	}

	public Class<?> getModelClass() {
		return CalendarNotificationTemplate.class;
	}

	public String getModelClassName() {
		return CalendarNotificationTemplate.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("calendarNotificationTemplateId",
			getCalendarNotificationTemplateId());
		attributes.put("calendarId", getCalendarId());
		attributes.put("notificationType", getNotificationType());
		attributes.put("notificationTemplateType", getNotificationTemplateType());
		attributes.put("field", getField());
		attributes.put("content", getContent());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long calendarNotificationTemplateId = (Long)attributes.get(
				"calendarNotificationTemplateId");

		if (calendarNotificationTemplateId != null) {
			setCalendarNotificationTemplateId(calendarNotificationTemplateId);
		}

		Long calendarId = (Long)attributes.get("calendarId");

		if (calendarId != null) {
			setCalendarId(calendarId);
		}

		String notificationType = (String)attributes.get("notificationType");

		if (notificationType != null) {
			setNotificationType(notificationType);
		}

		String notificationTemplateType = (String)attributes.get(
				"notificationTemplateType");

		if (notificationTemplateType != null) {
			setNotificationTemplateType(notificationTemplateType);
		}

		String field = (String)attributes.get("field");

		if (field != null) {
			setField(field);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}
	}

	/**
	* Returns the primary key of this calendar notification template.
	*
	* @return the primary key of this calendar notification template
	*/
	public long getPrimaryKey() {
		return _calendarNotificationTemplate.getPrimaryKey();
	}

	/**
	* Sets the primary key of this calendar notification template.
	*
	* @param primaryKey the primary key of this calendar notification template
	*/
	public void setPrimaryKey(long primaryKey) {
		_calendarNotificationTemplate.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this calendar notification template.
	*
	* @return the uuid of this calendar notification template
	*/
	public java.lang.String getUuid() {
		return _calendarNotificationTemplate.getUuid();
	}

	/**
	* Sets the uuid of this calendar notification template.
	*
	* @param uuid the uuid of this calendar notification template
	*/
	public void setUuid(java.lang.String uuid) {
		_calendarNotificationTemplate.setUuid(uuid);
	}

	/**
	* Returns the calendar notification template ID of this calendar notification template.
	*
	* @return the calendar notification template ID of this calendar notification template
	*/
	public long getCalendarNotificationTemplateId() {
		return _calendarNotificationTemplate.getCalendarNotificationTemplateId();
	}

	/**
	* Sets the calendar notification template ID of this calendar notification template.
	*
	* @param calendarNotificationTemplateId the calendar notification template ID of this calendar notification template
	*/
	public void setCalendarNotificationTemplateId(
		long calendarNotificationTemplateId) {
		_calendarNotificationTemplate.setCalendarNotificationTemplateId(calendarNotificationTemplateId);
	}

	/**
	* Returns the calendar ID of this calendar notification template.
	*
	* @return the calendar ID of this calendar notification template
	*/
	public long getCalendarId() {
		return _calendarNotificationTemplate.getCalendarId();
	}

	/**
	* Sets the calendar ID of this calendar notification template.
	*
	* @param calendarId the calendar ID of this calendar notification template
	*/
	public void setCalendarId(long calendarId) {
		_calendarNotificationTemplate.setCalendarId(calendarId);
	}

	/**
	* Returns the notification type of this calendar notification template.
	*
	* @return the notification type of this calendar notification template
	*/
	public java.lang.String getNotificationType() {
		return _calendarNotificationTemplate.getNotificationType();
	}

	/**
	* Sets the notification type of this calendar notification template.
	*
	* @param notificationType the notification type of this calendar notification template
	*/
	public void setNotificationType(java.lang.String notificationType) {
		_calendarNotificationTemplate.setNotificationType(notificationType);
	}

	/**
	* Returns the notification template type of this calendar notification template.
	*
	* @return the notification template type of this calendar notification template
	*/
	public java.lang.String getNotificationTemplateType() {
		return _calendarNotificationTemplate.getNotificationTemplateType();
	}

	/**
	* Sets the notification template type of this calendar notification template.
	*
	* @param notificationTemplateType the notification template type of this calendar notification template
	*/
	public void setNotificationTemplateType(
		java.lang.String notificationTemplateType) {
		_calendarNotificationTemplate.setNotificationTemplateType(notificationTemplateType);
	}

	/**
	* Returns the field of this calendar notification template.
	*
	* @return the field of this calendar notification template
	*/
	public java.lang.String getField() {
		return _calendarNotificationTemplate.getField();
	}

	/**
	* Sets the field of this calendar notification template.
	*
	* @param field the field of this calendar notification template
	*/
	public void setField(java.lang.String field) {
		_calendarNotificationTemplate.setField(field);
	}

	/**
	* Returns the content of this calendar notification template.
	*
	* @return the content of this calendar notification template
	*/
	public java.lang.String getContent() {
		return _calendarNotificationTemplate.getContent();
	}

	/**
	* Sets the content of this calendar notification template.
	*
	* @param content the content of this calendar notification template
	*/
	public void setContent(java.lang.String content) {
		_calendarNotificationTemplate.setContent(content);
	}

	public boolean isNew() {
		return _calendarNotificationTemplate.isNew();
	}

	public void setNew(boolean n) {
		_calendarNotificationTemplate.setNew(n);
	}

	public boolean isCachedModel() {
		return _calendarNotificationTemplate.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_calendarNotificationTemplate.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _calendarNotificationTemplate.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _calendarNotificationTemplate.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_calendarNotificationTemplate.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _calendarNotificationTemplate.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_calendarNotificationTemplate.setExpandoBridgeAttributes(baseModel);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_calendarNotificationTemplate.setExpandoBridgeAttributes(expandoBridge);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_calendarNotificationTemplate.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CalendarNotificationTemplateWrapper((CalendarNotificationTemplate)_calendarNotificationTemplate.clone());
	}

	public int compareTo(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		return _calendarNotificationTemplate.compareTo(calendarNotificationTemplate);
	}

	@Override
	public int hashCode() {
		return _calendarNotificationTemplate.hashCode();
	}

	public com.liferay.portal.model.CacheModel<CalendarNotificationTemplate> toCacheModel() {
		return _calendarNotificationTemplate.toCacheModel();
	}

	public CalendarNotificationTemplate toEscapedModel() {
		return new CalendarNotificationTemplateWrapper(_calendarNotificationTemplate.toEscapedModel());
	}

	public CalendarNotificationTemplate toUnescapedModel() {
		return new CalendarNotificationTemplateWrapper(_calendarNotificationTemplate.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _calendarNotificationTemplate.toString();
	}

	public java.lang.String toXmlString() {
		return _calendarNotificationTemplate.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_calendarNotificationTemplate.persist();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public CalendarNotificationTemplate getWrappedCalendarNotificationTemplate() {
		return _calendarNotificationTemplate;
	}

	public CalendarNotificationTemplate getWrappedModel() {
		return _calendarNotificationTemplate;
	}

	public void resetOriginalValues() {
		_calendarNotificationTemplate.resetOriginalValues();
	}

	private CalendarNotificationTemplate _calendarNotificationTemplate;
}