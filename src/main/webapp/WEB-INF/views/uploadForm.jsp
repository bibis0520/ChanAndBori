<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<form id="form1" action="uploadForm" method="post" enctype="multipart/form-data">
		<input type="file" name="file" />
		<input type="submit" />
	</form>

</div>

<%@ include file="include/footer.jsp" %>
