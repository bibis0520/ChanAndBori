<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="include/header.jsp" %>

<div class="contents overflow-h" style="padding:40px">

	<h4>Exception ---> ${exception.getMessage()}</h4>

	<ul>
		<c:forEach items="${exception.getStackTrace()}" var="stack">
			<li style="list-style:square !important;">${stack.toString()}</li>
		</c:forEach>
	</ul>

</div>

<%@ include file="include/footer.jsp" %>