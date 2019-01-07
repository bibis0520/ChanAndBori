<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<div class="container padding-t10">

		<form name="regiForm" class="overflow-a" method="post">
			<div class="form-group">
				<label for="title">Title</label>
				<input class="form-control" type="text" name="title" id="title" placeholder="Enter Title..."/>
			</div>

			<div class="form-group">
				<label for="smartEditor">Content</label>
				<textarea class="form-control" style="width:100%" rows="10" cols="40" name="content" id="smartEditor" placeholder="Enter Content..."></textarea>
			</div>

			<div class="form-group">
				<label for="regiUserId">Writer</label>
				<input class="form-control" type="text" name="regiUserId" id="regiUserId" placeholder="Enter Writer..."/>
			</div>
		</form>

		<div class="text-center">
			<button type="button" id="btnRegister"  class="btn btn-outline-primary m5">등록</button>
			<a href="/board/listPage${cri.makeQuery()}" class="btn btn-outline-danger m5">취소</a>
		</div>

	</div><!-- /.container -->

</div><!-- /.contents -->

<script>
$(document).ready(function(){

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
	       	oEditors.getById["smartEditor"].exec("PASTE_HTML", [ "" ]);
	    },
	    fCreator: "createSEditor2"
	});

	//등록 버튼
	$("#btnRegister").on("click", () => {

		oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);

		var title = $("#title").val(),
			content = $("#smartEditor").val(),
			regiUserId = $("#regiUserId").val(),
			editorContent = content.replace(/<\/?[a-z][a-z0-9]*[^<>]*>/ig, "");	//	<p>KimChan</p> ===>  KimChan
		console.log("title : " + title + ", content : " + content + ", regiUserId : " + regiUserId + ", editorContent : " + editorContent);

		if ( title == "" ) {
			alert("제목을 입력하세요.");
			document.regiForm.title.focus();
			return;
		}

		if(content == "" || content == null || content == '&nbsp;' || content == '<p>&nbsp;</p>' || content == '<p><br></p>' || editorContent == "" ) {
            alert("내용을 입력하세요.");
            oEditors.getById["smartEditor"].exec("FOCUS"); //포커싱
            return;
       	}

		if ( regiUserId == "" ) {
			alert("작성자를 입력하세요.");
			document.regiForm.regiUserId.focus();
			return;
		}

		try {
			document.regiForm.submit();
		} catch (e) {

		}
	});

	/* $("#editor_sample01").on("keydown",function(){
		console.error($(this).val().length)
		if($(this).val().length > 10){return false;}
	}) */
});
</script>

<%@ include file="../include/footer.jsp" %>