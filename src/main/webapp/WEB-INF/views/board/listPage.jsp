<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<h3>Board</h3>

<div class="m20 overflow-a">
	<a href="/board/register">
		<button class="btn btn-primary fr">새글등록</button>
	</a>
</div>

<div class="container-fluid">
	<table class="table table-bordered table-hover">
	  <thead>
		<tr>
			<th style="width: 10px">BNO</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>REGDATE</th>
			<th style="width: 40px">VIEWCNT</th>
		</tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${list}" var="boardVO">
		<tr class="boardRow" data-board-id="${boardVO.boardId}">
			<td>${boardVO.bno}</td>
			<td>${boardVO.title}</td>
			<td>${boardVO.regiUserId}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regiDate}"/></td>
			<td><span class="badge badge-pill badge-dark">${boardVO.viewCnt}</span></td>
		</tr>
	  </c:forEach>
	  </tbody>
	</table>
</div>

<form id="pageRedirect" method="post" action="/board/viewRead" style="display:none;">
	<input id="inpt_boardId" type="hidden" name="boardId" />
</form>

<a id="gotoRead"></a>

<script>
$(document).ready(function(){

	/* 테이블의 해당 행을 클릭하면 해당 게시물의 조회(read)페이지로 이동 */
	$(".boardRow").on('click', function(event){
		console.info($(this).index());	/* 3 */
		console.info($(this).children(".bno").text());	/* 404 */

		console.info("$(this).data() => ",$(this).data());
		console.info("$(this).data('boardId') => ",$(this).data("boardId"));
		$("#inpt_boardId").val($(this).data("boardId"));
		$("#pageRedirect").submit();
	});
});
</script>
<%@ include file="../include/footer.jsp" %>