var oEditors = [];

// Used in register.jsp
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
		fOnAppLoad : function() {
			//기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
			oEditors.getById["smartEditor"].exec("PASTE_HTML", [ content ]);
		},
		fCreator: "createSEditor2"
	});
};

// Used in modify.jsp
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