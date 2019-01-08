<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ include file="include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<div class="container padding-t10 col-md-4">
		<form>
		  	<div class="form-group">
		    	<label for="exampleInputId1">ID</label>
		    	<input type="email" class="form-control" id="exampleInputId1" placeholder="Enter your ID">
		  	</div>

		  	<div class="form-group">
		    	<label for="exampleInputPassword1">Password</label>
		    	<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Enter yout Password">
		  	</div>

		  	<div class="form-group form-check">
		    	<input type="checkbox" class="form-check-input" id="exampleCheck1">
		    	<label class="form-check-label" for="exampleCheck1">Remember Me</label>
		  	</div>

		</form>

		<div class="text-center">
		  	<button type="button" class="btn btn-primary m5">Login</button>
		  	<button type="button" class="btn btn-danger m5">Cancel</button>
			<a href="/signup" class="btn btn-success m5">SignUp</a>
		</div>

	</div><!-- /.container -->

</div><!-- /.contents -->

<%@ include file="include/footer.jsp" %>