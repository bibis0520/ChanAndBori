// NaverSmartEditor2.0

// https://github.com/naver/smarteditor2에서 설치한 dist파일 -> /src/main/webapp/resources/plugins/naverSmartEditor
// User Guide -> http://naver.github.io/smarteditor2/user_guide/

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var oEditors = [];

// Used in register.jsp (register.jsp에서는 naverSmartEditor가 적용된 textarea에 들어갈 값이 필요없다.)
function naverSmartEditorRegister(){
	oEditors = [];

	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "smartEditor",
		sSkinURI: "../../../resources/plugins/naverSmartEditor/SmartEditor2Skin.html",
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,

			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,

			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true,

			fOnBeforeUnload : function() {
			}
		},
		fCreator: "createSEditor2"
	});
};

// Used in modify.jsp (modify.jsp에서는 textarea에 db에서 불러온 기존 값들이 들어가야 하기 때문에 매개변수로 content를 받는다.)
function naverSmartEditorReadModify(content){
	oEditors = [];

	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "smartEditor",
	    sSkinURI: "../../../resources/plugins/naverSmartEditor/SmartEditor2Skin.html",
	    htParams : {
	    	// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
	       	bUseToolbar : true,

	       	// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
	       	bUseVerticalResizer : true,

	       	// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
	       	bUseModeChanger : true,

	       	fOnBeforeUnload : function() {
	       	}
	    },
	    fOnAppLoad : function() {
	       	//기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
	       	oEditors.getById["smartEditor"].exec("PASTE_HTML", [ content ]);
	    },
	    fCreator: "createSEditor2"
	});
};