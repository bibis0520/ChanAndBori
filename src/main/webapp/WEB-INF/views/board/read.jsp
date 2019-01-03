<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<div id="modifyOK" class="alert alert-success hidden text-center" role="alert">수정이 완료되었습니다.</div>

	<h3>READ</h3>

	<div class="container">
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
				<label for="content">Content</label>
				<textarea class="form-control" rows="10" cols="40" name="content" id="content" readonly>${boardVO.content}</textarea>
			</div>

			<div class="form-group">
				<label for="writer">Writer</label>
				<input class="form-control" type="text" name="regiUserId" id="regiUserId" value="${boardVO.regiUserId}" readonly/>
			</div>

			<div class="text-center">
				<a href="/board/modify?bno=${boardVO.bno}"><button id="btnModify" type="button" class="btn btn-outline-warning m5">수정</button></a>
				<a href="/board/remove?bno=${boardVO.bno}"><button id="btnDelete" type="button" class="btn btn-outline-danger m5">삭제</button></a>
				<button id="btnListPage" type="button" class="btn btn-outline-secondary m5"  >목록</button>
			</div>
		</form>
	</div>

	<form role="form" method="post">
		<input type="hidden" name="bno" value="${boardVO.bno}" />
	</form>

</div>

<script>
$(document).ready(function(){

	var modifyResult = "${result}";
	if( modifyResult === "Modify Success!!!" ) {
		$("#modifyOK").removeClass("hidden");
		$("#modifyOK").fadeOut(2000);
	};

	//취소 버튼
	$("#btnListPage").on("click", () => {
		window.location.href = "/board/listPage";
	});

});
</script>

<%@ include file="../include/footer.jsp" %>