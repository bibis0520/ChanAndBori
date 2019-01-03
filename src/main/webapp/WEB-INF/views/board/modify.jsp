<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<h3>MODIFY</h3>

	<div class="container">
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
				<label for="content">Content</label>
				<textarea class="form-control" rows="10" cols="40" name="content" id="content">${boardVO.content}</textarea>
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

	$("#btnEnter").on("click", () => {
		var $title = $("#title").val(),
			$content = $("#content").val();

		if( $title == "" ) {
			alert("제목을 입력해주세요.");
			document.modifyForm.title.focus();
			return;
		}

		if( $content == "" ) {
			alert("내용을 입력해주세요.");
			document.modifyForm.content.focus();
			return;
		}
		alert("수정이 완료되었습니다.");
		document.modifyForm.submit();
	});

	//취소 버튼
	$("#btnCancel").on("click", () => {

		window.location.href = "/board/listPage";
	});
});
</script>

<%@ include file="../include/footer.jsp" %>