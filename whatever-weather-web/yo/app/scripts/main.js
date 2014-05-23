/*global require*/
'use strict';

require.config({
	shim : {
		underscore : {
			exports : '_'
		},
		
		backbone : {
			deps : [ 'underscore', 'jquery' ],
			exports : 'Backbone'
		},
		
		backboneLocalstorage : {
			deps : [ 'backbone' ],
			exports : 'Store'
		},
		
		bootstrap : {
			deps : [ 'jquery' ],
			exports : 'jquery'
		}
	},

	paths : {
		jquery : '../bower_components/jquery/dist/jquery',
		backbone : '../bower_components/backbone/backbone',
		underscore : '../bower_components/underscore/underscore',
		bootstrap : '../bower_components/sass-bootstrap/dist/js/bootstrap',
		backboneLocalstorage : '../bower_components/backbone.localStorage/backbone.localStorage',
		text : '../bower_components/requirejs-text/text'
	}
});

require([ 'backbone', 'views/wtwview', 'routes/wtwrouter', 'models/wtwmodel',
		'collections/wtwcollection', 'common/wtwtemplates'

], function(Backbone, WtwView, WtwRouter, WtwModel, WtwCollection, WtwTemplates) {
	
	Backbone.View.prototype.close = function() {
		console.log('Closing view ' + this);
		if (this.beforeClose) {
			this.beforeClose();
		}
		this.remove();
		this.unbind();
	};
	
	WtwTemplates.loadTemplates(['main'], function() {
		
		new WtwRouter();
		
		Backbone.history.start({
			pushState : true
		});

		var model = new WtwModel({
			id : document.location.search.slice(1)
		});
		model.fetch();
		
		// Initialize the application view
		new WtwView({
			model : model
		});
		
	});
	
});
