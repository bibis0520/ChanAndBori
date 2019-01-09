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
		</form>

		<div class="text-center">
			<button id="btnEnter"  type="button" class="btn btn-outline-primary m5">확인</button>
			<a href="/board/read${cri.makeQuery()}&bno=${boardVO.bno}" class="btn btn-outline-secondary m5">취소</a>
		</div>

	</div><!-- /.container -->

	<form role="form" method="post">
		<input type="hidden" id="bno" name="bno" value="${boardVO.bno}" />
	</form>

</div><!-- /.contents -->

<script>
$(document).ready(function(){

	var content 		= `${boardVO.content}`,
		editorContent 	= content.replace(/<\/?[a-z][a-z0-9]*[^<>]*>/ig, "");	//	<p>KimChan</p> ===>  KimChan
		console.log("editorContent : " + editorContent + ", content : " + content);

	naverSmartEditorReadModify(content);

	//수정 확인 버튼
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

		document.modifyForm.submit();
	});

});
</script>

<%@ include file="../include/footer.jsp" %>