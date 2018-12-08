<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

   
<form method="post" class="text-center">

	<div>
		<label style="witdh:40px" for="">Title</label>
		<input type="text" name="title" placeholder="Enter Title..."/>
	</div>
	
	<div>
		<label style="witdh:40px" for="">Content</label>
		<textarea name="content" id="" rows="10" cols="40" placeholder="Enter Content..."></textarea>
	</div>
	
	<div>
		<label style="witdh:40px" for="">Writer</label>	
		<input type="text" name="writer" placeholder="Enter Writer..."/>
	</div>
	
	<div class="text-center">
		<button class="btn btn-primary" type="submit">등록</button>
		<a href="/board/listAll"><button class="btn btn-danger">취소</button></a>
	</div>
	
</form>
  
<%@ include file="../include/footer.jsp" %>  