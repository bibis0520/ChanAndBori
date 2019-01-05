<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript" src="../../../resources/plugins/jQuery/jQuery-2.1.4.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../../resources/plugins/naverSmartEditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
    <script type="text/javascript">
      $(document).ready(function(){
          fn_init();
      });

      function fn_init(){
        var oEditors = [];

        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: "editor_sample01",
            sSkinURI: "../../../resources/plugins/naverSmartEditor/SmartEditor2Skin.html",
            fCreator: "createSEditor2"
        });
      }


    </script>
  </head>
  <body>
    <textarea name="editor_sample01" id="editor_sample01" rows="10" cols="100"></textarea>
  </body>
</html>
