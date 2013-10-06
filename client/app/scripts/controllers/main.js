'use strict';

angular.module('WorkingOnApp')
  .controller('MainCtrl', function ($scope, $resource) {
    var setEntriesScope = function(data){
      $scope.entries = data.results;
    };

    var Entries = $resource("http://local.workingon.com/entries");
    Entries.get({}, setEntriesScope);

    $scope.createEntry = function(){
      $scope.entries.unshift({created_at: (new Date()).getTime(), text: this.newEntry});
    };
  });
