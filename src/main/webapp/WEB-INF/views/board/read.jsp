<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<div id="modifyOK" class="alert alert-success hidden text-center" role="alert">수정이 완료되었습니다.</div>

	<div class="container padding-t10">

		<form name="readForm" method="post">

			<div class="form-group">
				<label for="bno">Bno</label>
				<input class="form-control" type="text" value="${boardVO.bno}" readonly/>
			</div>

			<div class="form-group">
				<label for="title">Title</label>
				<input class="form-control" type="text" name="title" id="title" value="${boardVO.title}" readonly/>
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
				<a href="/board/modify${cri.makeQuery()}&bno=${boardVO.bno}">
					<button id="btnModify" type="button" class="btn btn-outline-warning m5">수정</button>
				</a>
				<a href="/board/listPage${cri.makeQuery()}" class="btn btn-outline-secondary m5">목록</a>
				<button id="btnRemove" class="btn btn-outline-danger m5">삭제</button>
			</div>
		</form>
	</div>

</div>

<script>
$(document).ready(function(){

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

	var modifyResult = "${result}";
	if( modifyResult === "Modify Success!!!" ) {
		$("#modifyOK").removeClass("hidden");
		$("#modifyOK").fadeOut(2000);
	};

	$("btnRemove").on("click", function(){
		if(confirm("정말로 삭제하시겠습니까?"))
			self.location.href = "/board/remove${cri.makeQuery()}&bno=${boardVO.bno}";
	});

});
</script>

<%@ include file="../include/footer.jsp" %>