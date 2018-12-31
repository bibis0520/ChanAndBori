<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<h3>New Board</h3>

<div class="container">
	<form class="overflow-a" method="post">

		<div class="form-group">
			<label for="title">Title</label>
			<input class="form-control" type="text" name="title" id="title" placeholder="Enter Title..."/>
		</div>

		<div class="form-group">
			<label for="content">Content</label>
			<textarea class="form-control" name="content" rows="10" cols="40" id="content" placeholder="Enter Content..."></textarea>
		</div>

		<div class="form-group">
			<label for="writer">Writer</label>
			<input class="form-control" type="text" name="regiUserId" id="regiUserId" placeholder="Enter Writer..."/>
		</div>

		<div class="text-center">
			<button class="btn btn-outline-primary m5" type="submit">등록</button>
			<!-- To-Do 취소버튼 누르면 게시물 등록되지 않고 단순히 리스트 페이지로만 되돌아가도록. -->
			<a href="/board/listPage"><button class="btn btn-outline-danger m5">취소</button></a>
		</div>

	</form>
</div>

<%@ include file="../include/footer.jsp" %>