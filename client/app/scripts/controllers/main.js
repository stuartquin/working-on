'use strict';

angular.module('WorkingOnApp')
  .controller('MainCtrl', function ($scope) {
    $scope.entries = [
      {created_at: "4 hours ago", text: "Began learning clojure"}
    ];
    $scope.createEntry = function(){
      $scope.entries.unshift({created_at: "now", text: this.newEntry});
    };
  });
