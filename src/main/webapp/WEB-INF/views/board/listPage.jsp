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

<div>
	<table class="table table-bordered">
		<tr>
			<th style="width: 10px">BNO</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>REGDATE</th>
			<th style="width: 40px">VIEWCNT</th>
		</tr>
	  <c:forEach items="${list}" var="boardVO">
		<tr class="boardRow">
			<td class="bno">${boardVO.bno}</td>
			<td>${boardVO.title}</td>
			<td>${boardVO.writer}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}" /></td>
			<td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
		</tr>
	  </c:forEach>
	</table>
</div> 
<form id="pageRedirect" method="get" action="/board/viewRead" style="display:none;">
	<input id="inputBno" type="hidden" name="bno" />
</form>
<a id="gotoRead"></a>
<script>
$(".boardRow").on('click', function(event){
	/* console.error(arguments); */
	/* console.info(this); */
	/* console.warn($(this)); */
	console.info($(this).index);
	console.info($(this).index());	/* 3 */
	console.info($(this).children(".bno"));
	console.info($(this).children(".bno").text());	/* 404 */
	
	var bno = $(this).children(".bno").text();
	$("#inputBno").val(bno);
	//$("#pageRedirect").submit();
	$("#gotoRead").attr("href","/board/viewRead?bno=" + bno).click();
	
});	
</script>
<%@ include file="../include/footer.jsp" %> 