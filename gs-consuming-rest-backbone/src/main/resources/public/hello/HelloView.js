define(function(require) {
	var Backbone = require('Backbone');
	var $ = require('jquery');
	var _ = require('underscore');

	return Backbone.View.extend({
		initialize: function() {
			this.template = _.template($('#hello-template').html());
			this.listenTo(this.model, 'change', this.render);
		},

		render: function(){
			this.$el.html(this.template(this.model.attributes));
		}
	});
})
