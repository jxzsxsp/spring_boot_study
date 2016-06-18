define(function(require) {
	var Backbone = require('Backbone');

	return Backbone.Model.extend({
		urlRoot: 'http://rest-service.guides.spring.io/greeting',
		url: function() {
			return this.urlRoot + '?name=' + this.id;
		}
	});
});
