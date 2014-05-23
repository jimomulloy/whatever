/*global define*/

define([
    'underscore',
    'backbone',
    'models/wtwmodel'
], function (_, Backbone, WtwModel) {
    'use strict';

    var WtwCollection = Backbone.Collection.extend({
        model: WtwModel
    });

    return WtwCollection;
});
