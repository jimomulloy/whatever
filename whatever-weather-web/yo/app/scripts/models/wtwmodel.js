/*global define*/

define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    'use strict';

    var WtwModel = Backbone.Model.extend({
    	
    	urlRoot: 'http://rest-service.guides.spring.io/greeting',
        
		url: function() {
			return this.urlRoot + '?name=' + this.id;
		},
		
		initialize: function() {
        },

        defaults: {
    		remaining: 1,
    		completed : 20,
    		item : 'Item1'
        },

        validate: function(attrs, options) {
        },

        parse: function(response, options)  {
            return response;
        }
    });

    return WtwModel;
});
