/*global define*/

define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/main.html',
    'models/wtwmodel',
    'common/wtwtemplates'
], function ($, _, Backbone, MainTemplate, WtwModel, WtwTemplates) {
    'use strict';

    var WtwView = Backbone.View.extend({
    	
    	// Instead of generating a new element, bind to the existing skeleton of
    	// the App already present in the HTML.
    	el: '#hello',
    
        initialize: function() {
			this.template = _.template(WtwTemplates.get('main'));
			this.listenTo(this.model, 'change', this.render);
		},

		//render: function(){
		//	console.log('render model remaining:'+this.model.remaining);
		//	this.$el.html(this.template(this.model.attributes));
		//},
	        
		tagName: 'div',

        id: '',

        className: '',

        events: {},

        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
        }
    });

    return WtwView;
});
