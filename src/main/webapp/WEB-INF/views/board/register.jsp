<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/pagingMethod.jsp" %>
<%@ include file="../include/pagingVariable.jsp" %>

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
			<button type="button" id="btnCancel" class="btn btn-outline-danger m5">안되는 취소</button>
		</div>

	</div><!-- /.container -->

</div><!-- /.contents -->

<script>
$(document).ready(function(){

	naverSmartEditorRegister();

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

		document.regiForm.submit();
	});

	$("#btnCancel").on("click", function(){

		var pageNum = "${boardSO.pageNum}";
		var perPageNum = "${boardSO.perPageNum}";

		pageNum = parseInt(pageNum);
		perPageNum = parseInt(perPageNum);

		console.log("pageNum : " + pageNum + ", perPageNum : " + perPageNum);

		var uri = `<%=makeURI(pageNum, perPageNum)%>`;

		console.log(uri);
	});

});
</script>

<%@ include file="../include/footer.jsp" %>