<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<h1 style="margin-bottom:20px">Board</h1>

<div class="text-center" style="margin-bottom:20px">
	<a href="/board/register">
		<button class="btn btn-primary">새글등록</button>
	</a>	
</div>

<table class="table table-bordered">

    <tr>
		<th style="width: 10px">BNO</th>
		<th>TITLE</th>
		<th>WRITER</th>
		<th>REGDATE</th>
		<th style="width: 40px">VIEWCNT</th>
	</tr>
	
  <c:forEach items="${list}" var="boardVO">
	<tr>
		<td>${boardVO.bno}</td>
		<td>${boardVO.title}</td>
		<td>${boardVO.writer}</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}" /></td>
		<td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
	</tr>
  </c:forEach>
	
</table> 
   
<%@ include file="../include/footer.jsp" %> 