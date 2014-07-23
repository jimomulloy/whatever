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
var Forecast = Backbone.Model.extend({
	  url: function() {
	    return "http://api.wunderground.com/api/7eaec3b21b154448/conditions/q/" + this.get( "zip" ) + ".json";
	  },
	  parse : function( data, xhr ) {
	    var observation = data.current_observation;
	    return {
	      id: observation.display_location.zip,
	      url: observation.icon_url,
	      state: observation.display_location.state_name,
	      zip: observation.display_location.zip,
	      city: observation.display_location.city,
	      temperature: observation.temp_f
	    };
	  }
	});