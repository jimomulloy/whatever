/*global define*/

define([
    'underscore',
    'backbone',
    'models/wtw'
], function (_, Backbone, WtwModel) {
    'use strict';

    var WtwCollection = Backbone.Collection.extend({
        model: WtwModel
    });

    return WtwCollection;
});
