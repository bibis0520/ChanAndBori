<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<div class="container padding-t10">
		<form name="modifyForm" class="overflow-a" method="post">

			<div class="form-group">
				<label for="bno">Bno</label>
				<input class="form-control" type="text" value="${boardVO.bno}" readonly/>
			</div>

			<div class="form-group">
				<label for="title">Title</label>
				<input class="form-control" type="text" name="title" id="title" value="${boardVO.title}" />
			</div>

			<div class="form-group">
				<label for="smartEditor">Content</label>
				<textarea class="form-control" style="width:100%" rows="10" cols="40" name="content" id="smartEditor"></textarea>
			</div>

			<div class="form-group">
				<label for="writer">Writer</label>
				<input class="form-control" type="text" name="regiUserId" id="regiUserId" value="${boardVO.regiUserId}" readonly/>
			</div>

			<div class="text-center">
				<button id="btnEnter"  type="button" class="btn btn-outline-primary m5"  >확인</button>
				<button id="btnCancel" type="button" class="btn btn-outline-secondary m5">취소</button>
			</div>
		</form>
	</div>

	<form role="form" method="post">
		<input type="hidden" name="bno" value="${boardVO.bno}" />
	</form>

</div>

<script>
$(document).ready(function(){
	/*  var $title = $("#title").val(),
			$content = $("#content").val(),
			$regiUserId = $("#regiUserId").val();
	    console.log("title : " + $title + ", content : " + $content + ", regiUserId : " + $regiUserId);
	*/

	var content 		= `${boardVO.content}`,
		editorContent 	= content.replace(/<\/?[a-z][a-z0-9]*[^<>]*>/ig, "");	//	<p>KimChan</p> ===>  KimChan
		console.log("editorContent : " + editorContent + ", content : " + content);

	var oEditors = [];
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

	//수정 버튼
	$("#btnEnter").on("click", () => {

		oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);

		var title = $("#title").val(),
			content = $("#smartEditor").val(),
			editorContent = content.replace(/<\/?[a-z][a-z0-9]*[^<>]*>/ig, "");	//	<p>KimChan</p> ===>  KimChan
		console.log("title : " + title + ", content : " + content + ", editorContent : " + editorContent);

		if( title == "" ) {
			alert("제목을 입력해주세요.");
			document.modifyForm.title.focus();
			return;
		}

		if(content == "" || content == null || content == '&nbsp;' || content == '<p>&nbsp;</p>' || content == '<p><br></p>' || editorContent == "" ) {
            alert("내용을 입력하세요.");
            oEditors.getById["smartEditor"].exec("FOCUS"); //포커싱
            return;
       	}

		/* alert("수정이 완료되었습니다."); */
		document.modifyForm.submit();
	});

	//취소 버튼
	$("#btnCancel").on("click", () => {

		window.location.href = "/board/listPage";
	});
});
</script>

<%@ include file="../include/footer.jsp" %>