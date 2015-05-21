(function() {
	AUI.add(
		'liferay-calendar-booking-source',
		function(A) {
			var CalendarBookingSource = A.Component.create(
				{
					NAME: 'calendar-booking-source',

					prototype: {
						getEvents: function(startDate, endDate, callback) {
							CalendarUtil.getEvents(
									startDate,
									endDate,
									[CalendarWorkflow.STATUS_APPROVED, CalendarWorkflow.STATUS_MAYBE, CalendarWorkflow.STATUS_PENDING],
									callback
								);
						},

						getBusyDays: function(startDate, endDate) {

						},

						getPopoverTemplate: function() {

						},

						getContainers: function() {

						},

						getViewURL: function() {

						},

						getEditURL: function() {

						},

						getIcon: function() {

						}
					}
				}
			);

			Liferay.CalendarBookingSource = CalendarBookingSource;
		},
		'',
		{
			requires: ['aui-base', 'aui-template-deprecated', 'event-outside', 'event-touch', 'widget-modality', 'widget-position', 'widget-position-align', 'widget-position-constrain', 'widget-stack', 'widget-stdmod']
		}
	);
})();