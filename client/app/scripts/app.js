'use strict';
var deps = ["ngResource", "angularMoment"];
var WorkingOnApp = angular.module('WorkingOnApp', deps);

WorkingOnApp.config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
