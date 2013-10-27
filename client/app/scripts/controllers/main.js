'use strict';

angular.module('WorkingOnApp')
  .controller('MainCtrl', function ($scope, $resource) {
    $scope.entries = [];
    var setEntriesScope = function(data){
      $scope.entries = data.results;
    };

    var Entry = $resource("http://local.workingon.com/entries");
    Entry.get({}, setEntriesScope);

    var editor = ace.edit("editor");
    editor.setTheme("ace/theme/solarized_light");
    editor.getSession().setMode("ace/mode/markdown");
    editor.renderer.setShowGutter(false);
    editor.setFontSize("16px");
    editor.setShowPrintMargin(false);
    editor.setHighlightActiveLine(false);

    $scope.createEntry = function(){
      var entry = new Entry();

      entry.text = editor.getSession().getValue();
      entry.tags = this.tags.split(" ");
      entry.$save();
      $scope.entries.unshift({
        created_at: (new Date()).getTime(),
        text: entry.text,
        tags: entry.tags
      });

      editor.getSession().setValue("");
      $scope.tags = "";
    };
  });
