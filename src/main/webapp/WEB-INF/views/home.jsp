<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="include/header.jsp" %>

<div class="contents overflow-h padding-b40">

    <h3>안녕하세요! 보리와 저의 일상을 기록하는 공간입니다!</h3>

    <div id="carouselExampleIndicators" class="carousel slide mx-auto align-middle" data-ride="carousel">
    	<ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
     	        <img class="d-block w-100" src="../../resources/images/mainpic1.png" alt="First slide">
			</div>
            <div class="carousel-item">
                <img class="d-block w-100" src="../../resources/images/mainpic2.JPG" alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="../../resources/images/mainpic3.JPG" alt="Third slide">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
        	<span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
    	</a>
	</div>

</div>
<%@ include file="include/footer.jsp" %>
