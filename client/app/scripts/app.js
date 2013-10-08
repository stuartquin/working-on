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

WorkingOnApp.directive('markdown', ['$timeout', function ($timeout) {
  var converter = new Showdown.converter();
  return {
    restrict: 'A',
    scope: true,
    link: function (scope, elem, attrs, ngModel) {
      debugger;
      elem.text(converter.makeHtml(elem.text()));
    }
  };
}]);
