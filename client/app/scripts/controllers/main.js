'use strict';

angular.module('WorkingOnApp')
  .controller('MainCtrl', function ($scope, $resource) {
    var setEntriesScope = function(data){
      $scope.entries = data.results;
    };

    var Entry = $resource("http://local.workingon.com/entries");
    Entry.get({}, setEntriesScope);

    $scope.createEntry = function(){
      var entry = new Entry();
      entry.text = this.newEntry;
      entry.$save();
      $scope.entries.unshift({created_at: (new Date()).getTime(), text: this.newEntry});
    };
  });
