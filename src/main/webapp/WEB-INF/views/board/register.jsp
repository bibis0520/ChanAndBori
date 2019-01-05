<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<h3>New Board</h3>

	<div class="container">
		<form name="regiForm" class="overflow-a" method="post">

			<div class="form-group">
				<label for="title">Title</label>
				<input class="form-control" type="text" name="title" id="title" placeholder="Enter Title..."/>
			</div>

			<div class="form-group">
				<label for="content">Content</label>
				<textarea class="form-control" rows="10" cols="40" name="content" id="content" placeholder="Enter Content..."></textarea>
			</div>

			<div class="form-group">
				<label for="regiUserId">Writer</label>
				<input class="form-control" type="text" name="regiUserId" id="regiUserId" placeholder="Enter Writer..."/>
			</div>

			<div class="text-center">
				<button type="button" id="btnRegister"  class="btn btn-outline-primary m5">등록</button>
				<button type="button" id="btnCancel" 	class="btn btn-outline-danger  m5">취소</button>
			</div>

		</form>
	</div>

</div>

<script>
$(document).ready(function(){

	$("#btnRegister").on("click", () => {
		/* DONE  1. 등록버튼을 누를 경우에는 TITLE, CONTENT, REGI_USER_ID가 모두 존재해야 한다. */
		/* TO-DO 2. 1의 조건이 충족되면 등록 버튼이 위의 세값이 모두 들어왔을 경우 생기고 없을경우 사라지게 하는것도 추가하면 공부될 듯 */

		var $title 		= $("#title").val(),		/* var title = $("input[name=title]").val(); */
			$content 	= $("#content").val(),
			$regiUserId 	= $("#regiUserId").val();

		if ( $title == "" ) {
			alert("제목을 입력하세요.");
			document.regiForm.title.focus();
			return;
		}

		if ( $content == "" ) {
			alert("내용을 입력하세요.");
			document.regiForm.content.focus();
			return;
		}

		if ( $regiUserId == "" ) {
			alert("작성자를 입력하세요.");
			document.regiForm.regiUserId.focus();
			return;
		}

		/* alert("\"" + $title + "\"이 등록되었습니다."); */
		document.regiForm.submit();
	});

	$("#btnCancel").on("click", () => {

		window.location.href = "/board/listPage";
	});

	$("#content").on("keydown",function(){
		console.error($(this).val().length)
		if($(this).val().length > 10){return false;}
	})
});
</script>

<%@ include file="../include/footer.jsp" %>