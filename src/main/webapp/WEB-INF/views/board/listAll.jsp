<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<div id="registerOK" class="alert alert-success hidden text-center" role="alert">새글이 등록되었습니다.</div>
	<div id="removeOK" class="alert alert-danger hidden text-center" role="alert">글이 삭제되었습니다.</div>

	<div class="m20 overflow-a">
		<a href="/board/register">
			<button class="btn btn-primary fr">새글등록</button>
		</a>
	</div>

	<div class="container-fluid">
		<table class="table table-bordered table-hover">
		  <thead>
			<tr>
				<th scope="col" style="width: 5%">BNO</th>
				<th scope="col" style="width: 50%">TITLE</th>
				<th scope="col" style="width: 30%">WRITER</th>
				<th scope="col" style="width: 10%">REGDATE</th>
				<th scope="col" style="width: 5%">VIEWCNT</th>
			</tr>
		  </thead>
		  <tbody>
		  <c:forEach items="${list}" var="boardVO">
			<tr class="boardRow" data-board-id="${boardVO.boardId}">
				<th scope="row" class="bno">${boardVO.bno}</th>
				<td>${boardVO.title}</td>
				<td>${boardVO.regiUserId}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regiDate}"/></td>
				<td class="text-center"><span class="badge badge-pill badge-dark">${boardVO.viewCnt}</span></td>
			</tr>
		  </c:forEach>
		  </tbody>
		</table>
	</div>

</div>

<script>
$(document).ready(function(){

	var result = '${result}';
	$(function(){
		if(result === "Register Success!!!") {
			$("#registerOK").removeClass("hidden");
			$("#registerOK").fadeOut(2000);
		}
		if(result === "Remove Success!!!") {
			$("#removeOK").removeClass("hidden");
			$("#removeOK").fadeOut(2000);
		}
	});

	$(".boardRow").on('click', function(){

		var boardId = $(this).data("boardId"),
		    uri = "/board/read?boardId=" + boardId;

		window.location.href = uri;
	});

});
</script>
<%@ include file="../include/footer.jsp" %>