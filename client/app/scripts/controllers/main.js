'use strict';

angular.module('WorkingOnApp')
  .controller('MainCtrl', function ($scope, $resource) {
    $scope.entries = [];
    var setEntriesScope = function(data){
      $scope.entries = data.results;
    };

    var getTagsFromEditor = function(editor){
      var session = editor.getSession();
      var tokens = [];
      for ( var i =0; i < session.getLength(); i++){
        tokens = tokens.concat(session.getTokens(i));
      }
      console.log(tokens);
      var tokens = tokens.filter(function(token){ 
        return token.type == "custom.tag";
      });  
      return tokens.map(function(token){return token.value.substr(1);});
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
      entry.tags = getTagsFromEditor(editor);
      console.log(entry);
      entry.$save();
      $scope.entries.unshift({created_at: (new Date()).getTime(), text: entry.text});
    };
  });
