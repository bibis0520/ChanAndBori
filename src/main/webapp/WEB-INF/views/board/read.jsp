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
		</form>

		<div class="text-center">
			<a href="/board/modify${cri.makeQuery()}&boardId=${boardVO.boardId}" class="btn btn-outline-warning m5">수정</a>
			<a href="/board/listPage${cri.makeQuery()}" class="btn btn-outline-secondary m5">목록</a>
			<button id="btnRemove" class="btn btn-outline-danger m5">삭제</button>
		</div>

	</div><!-- /.container -->

</div><!-- /.contents -->

<script>
$(document).ready(function(){

	var content 		= `${boardVO.content}`,
		editorContent 	= content.replace(/<\/?[a-z][a-z0-9]*[^<>]*>/ig, "");

	naverSmartEditorReadModify(content);

	var modifyResult = "${result}";
	if( modifyResult === "Modify Success!!!" ) {
		$("#modifyOK").removeClass("hidden");
		$("#modifyOK").fadeOut(3000);
	};

	$("#btnRemove").on("click", function(){
		if(confirm("정말로 삭제하시겠습니까?"))
			self.location.href = "/board/remove${cri.makeQuery()}&boardId=${boardVO.boardId}";
	});

});
</script>

<%@ include file="../include/footer.jsp" %>