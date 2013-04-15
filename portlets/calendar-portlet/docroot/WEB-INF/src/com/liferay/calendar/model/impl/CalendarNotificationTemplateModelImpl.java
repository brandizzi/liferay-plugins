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

package com.liferay.calendar.model.impl;

import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.model.CalendarNotificationTemplateModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the CalendarNotificationTemplate service. Represents a row in the &quot;CalendarNotificationTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.calendar.model.CalendarNotificationTemplateModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CalendarNotificationTemplateImpl}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplateImpl
 * @see com.liferay.calendar.model.CalendarNotificationTemplate
 * @see com.liferay.calendar.model.CalendarNotificationTemplateModel
 * @generated
 */
public class CalendarNotificationTemplateModelImpl extends BaseModelImpl<CalendarNotificationTemplate>
	implements CalendarNotificationTemplateModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a calendar notification template model instance should use the {@link com.liferay.calendar.model.CalendarNotificationTemplate} interface instead.
	 */
	public static final String TABLE_NAME = "CalendarNotificationTemplate";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "calendarNotificationTemplateId", Types.BIGINT },
			{ "calendarId", Types.BIGINT },
			{ "notificationType", Types.VARCHAR },
			{ "notificationTemplateType", Types.VARCHAR },
			{ "field", Types.VARCHAR },
			{ "content", Types.CLOB }
		};
	public static final String TABLE_SQL_CREATE = "create table CalendarNotificationTemplate (uuid_ VARCHAR(75) null,calendarNotificationTemplateId LONG not null primary key,calendarId LONG,notificationType VARCHAR(75) null,notificationTemplateType VARCHAR(75) null,field VARCHAR(75) null,content TEXT null)";
	public static final String TABLE_SQL_DROP = "drop table CalendarNotificationTemplate";
	public static final String ORDER_BY_JPQL = " ORDER BY calendarNotificationTemplate.calendarNotificationTemplateId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CalendarNotificationTemplate.calendarNotificationTemplateId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.calendar.model.CalendarNotificationTemplate"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.calendar.model.CalendarNotificationTemplate"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.calendar.model.CalendarNotificationTemplate"),
			true);
	public static long CALENDARID_COLUMN_BITMASK = 1L;
	public static long FIELD_COLUMN_BITMASK = 2L;
	public static long NOTIFICATIONTEMPLATETYPE_COLUMN_BITMASK = 4L;
	public static long NOTIFICATIONTYPE_COLUMN_BITMASK = 8L;
	public static long UUID_COLUMN_BITMASK = 16L;
	public static long CALENDARNOTIFICATIONTEMPLATEID_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.calendar.model.CalendarNotificationTemplate"));

	public CalendarNotificationTemplateModelImpl() {
	}

	public long getPrimaryKey() {
		return _calendarNotificationTemplateId;
	}

	public void setPrimaryKey(long primaryKey) {
		setCalendarNotificationTemplateId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return _calendarNotificationTemplateId;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return CalendarNotificationTemplate.class;
	}

	public String getModelClassName() {
		return CalendarNotificationTemplate.class.getName();
	}

	@Override
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

	@Override
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

	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	public long getCalendarNotificationTemplateId() {
		return _calendarNotificationTemplateId;
	}

	public void setCalendarNotificationTemplateId(
		long calendarNotificationTemplateId) {
		_calendarNotificationTemplateId = calendarNotificationTemplateId;
	}

	public long getCalendarId() {
		return _calendarId;
	}

	public void setCalendarId(long calendarId) {
		_columnBitmask |= CALENDARID_COLUMN_BITMASK;

		if (!_setOriginalCalendarId) {
			_setOriginalCalendarId = true;

			_originalCalendarId = _calendarId;
		}

		_calendarId = calendarId;
	}

	public long getOriginalCalendarId() {
		return _originalCalendarId;
	}

	public String getNotificationType() {
		if (_notificationType == null) {
			return StringPool.BLANK;
		}
		else {
			return _notificationType;
		}
	}

	public void setNotificationType(String notificationType) {
		_columnBitmask |= NOTIFICATIONTYPE_COLUMN_BITMASK;

		if (_originalNotificationType == null) {
			_originalNotificationType = _notificationType;
		}

		_notificationType = notificationType;
	}

	public String getOriginalNotificationType() {
		return GetterUtil.getString(_originalNotificationType);
	}

	public String getNotificationTemplateType() {
		if (_notificationTemplateType == null) {
			return StringPool.BLANK;
		}
		else {
			return _notificationTemplateType;
		}
	}

	public void setNotificationTemplateType(String notificationTemplateType) {
		_columnBitmask |= NOTIFICATIONTEMPLATETYPE_COLUMN_BITMASK;

		if (_originalNotificationTemplateType == null) {
			_originalNotificationTemplateType = _notificationTemplateType;
		}

		_notificationTemplateType = notificationTemplateType;
	}

	public String getOriginalNotificationTemplateType() {
		return GetterUtil.getString(_originalNotificationTemplateType);
	}

	public String getField() {
		if (_field == null) {
			return StringPool.BLANK;
		}
		else {
			return _field;
		}
	}

	public void setField(String field) {
		_columnBitmask |= FIELD_COLUMN_BITMASK;

		if (_originalField == null) {
			_originalField = _field;
		}

		_field = field;
	}

	public String getOriginalField() {
		return GetterUtil.getString(_originalField);
	}

	public String getContent() {
		if (_content == null) {
			return StringPool.BLANK;
		}
		else {
			return _content;
		}
	}

	public void setContent(String content) {
		_content = content;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			CalendarNotificationTemplate.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CalendarNotificationTemplate toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CalendarNotificationTemplate)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CalendarNotificationTemplateImpl calendarNotificationTemplateImpl = new CalendarNotificationTemplateImpl();

		calendarNotificationTemplateImpl.setUuid(getUuid());
		calendarNotificationTemplateImpl.setCalendarNotificationTemplateId(getCalendarNotificationTemplateId());
		calendarNotificationTemplateImpl.setCalendarId(getCalendarId());
		calendarNotificationTemplateImpl.setNotificationType(getNotificationType());
		calendarNotificationTemplateImpl.setNotificationTemplateType(getNotificationTemplateType());
		calendarNotificationTemplateImpl.setField(getField());
		calendarNotificationTemplateImpl.setContent(getContent());

		calendarNotificationTemplateImpl.resetOriginalValues();

		return calendarNotificationTemplateImpl;
	}

	public int compareTo(
		CalendarNotificationTemplate calendarNotificationTemplate) {
		long primaryKey = calendarNotificationTemplate.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		CalendarNotificationTemplate calendarNotificationTemplate = null;

		try {
			calendarNotificationTemplate = (CalendarNotificationTemplate)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = calendarNotificationTemplate.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		CalendarNotificationTemplateModelImpl calendarNotificationTemplateModelImpl =
			this;

		calendarNotificationTemplateModelImpl._originalUuid = calendarNotificationTemplateModelImpl._uuid;

		calendarNotificationTemplateModelImpl._originalCalendarId = calendarNotificationTemplateModelImpl._calendarId;

		calendarNotificationTemplateModelImpl._setOriginalCalendarId = false;

		calendarNotificationTemplateModelImpl._originalNotificationType = calendarNotificationTemplateModelImpl._notificationType;

		calendarNotificationTemplateModelImpl._originalNotificationTemplateType = calendarNotificationTemplateModelImpl._notificationTemplateType;

		calendarNotificationTemplateModelImpl._originalField = calendarNotificationTemplateModelImpl._field;

		calendarNotificationTemplateModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CalendarNotificationTemplate> toCacheModel() {
		CalendarNotificationTemplateCacheModel calendarNotificationTemplateCacheModel =
			new CalendarNotificationTemplateCacheModel();

		calendarNotificationTemplateCacheModel.uuid = getUuid();

		String uuid = calendarNotificationTemplateCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			calendarNotificationTemplateCacheModel.uuid = null;
		}

		calendarNotificationTemplateCacheModel.calendarNotificationTemplateId = getCalendarNotificationTemplateId();

		calendarNotificationTemplateCacheModel.calendarId = getCalendarId();

		calendarNotificationTemplateCacheModel.notificationType = getNotificationType();

		String notificationType = calendarNotificationTemplateCacheModel.notificationType;

		if ((notificationType != null) && (notificationType.length() == 0)) {
			calendarNotificationTemplateCacheModel.notificationType = null;
		}

		calendarNotificationTemplateCacheModel.notificationTemplateType = getNotificationTemplateType();

		String notificationTemplateType = calendarNotificationTemplateCacheModel.notificationTemplateType;

		if ((notificationTemplateType != null) &&
				(notificationTemplateType.length() == 0)) {
			calendarNotificationTemplateCacheModel.notificationTemplateType = null;
		}

		calendarNotificationTemplateCacheModel.field = getField();

		String field = calendarNotificationTemplateCacheModel.field;

		if ((field != null) && (field.length() == 0)) {
			calendarNotificationTemplateCacheModel.field = null;
		}

		calendarNotificationTemplateCacheModel.content = getContent();

		String content = calendarNotificationTemplateCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			calendarNotificationTemplateCacheModel.content = null;
		}

		return calendarNotificationTemplateCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", calendarNotificationTemplateId=");
		sb.append(getCalendarNotificationTemplateId());
		sb.append(", calendarId=");
		sb.append(getCalendarId());
		sb.append(", notificationType=");
		sb.append(getNotificationType());
		sb.append(", notificationTemplateType=");
		sb.append(getNotificationTemplateType());
		sb.append(", field=");
		sb.append(getField());
		sb.append(", content=");
		sb.append(getContent());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.calendar.model.CalendarNotificationTemplate");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarNotificationTemplateId</column-name><column-value><![CDATA[");
		sb.append(getCalendarNotificationTemplateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarId</column-name><column-value><![CDATA[");
		sb.append(getCalendarId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notificationType</column-name><column-value><![CDATA[");
		sb.append(getNotificationType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notificationTemplateType</column-name><column-value><![CDATA[");
		sb.append(getNotificationTemplateType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>field</column-name><column-value><![CDATA[");
		sb.append(getField());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = CalendarNotificationTemplate.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			CalendarNotificationTemplate.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _calendarNotificationTemplateId;
	private long _calendarId;
	private long _originalCalendarId;
	private boolean _setOriginalCalendarId;
	private String _notificationType;
	private String _originalNotificationType;
	private String _notificationTemplateType;
	private String _originalNotificationTemplateType;
	private String _field;
	private String _originalField;
	private String _content;
	private long _columnBitmask;
	private CalendarNotificationTemplate _escapedModel;
}