<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="include/header.jsp" %>

<style>
.fileDrop{
	width:100%;
	height:200px;
	border:3px dotted blue;
}

small{
	margin-left:3px;
	font-weight:bold;
	color:gray;
}
</style>

<div class="contents overflow-h padding-b40">

	<h3>Ajax File Upload</h3>
	<div class="fileDrop"></div>

	<div class="uploadedList"></div>

</div>

<script>
	/**
 	* 정규표현식을 이용하여 파일의 확장자가 존재하는지를 검사함(i의 의미는 대, 소문자를 구분하지 않는다는 뜻)
 	*/
	function checkImageType(fileName){
		var pattern = /jpg$|gif$|png$|jpeg$/i;
		return fileName.match(pattern);
	};

	/**
	* jsp에서 출력되는 이름을 정해주는 함수
	*/
	function getOriginalName(fileName){
		if ( checkImageType(fileName) ) {
			return;
		}

		var idx = fileName.indexOf("_") + 1;
		return fileName.substr(idx);
	};

	function getImageLink(fileName){
		if ( !checkImageType(fileName) ) {
			return;
		}

		var front = fileName.substr(0, 12);
		var end = fileName.substr(14);

		console.log("fileName : " + fileName);
		console.log("front : " + front);
		console.log("end : " + end);
		return front + end;
	}

	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});

	$(".fileDrop").on("drop", function(event){
		event.preventDefault();

		var files = event.originalEvent.dataTransfer.files;

		var file = files[0];

		console.log(file);

		var formData = new FormData();

		formData.append("file", file);

		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType: "text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){

				var str = "";

				console.log(data);
				console.log(checkImageType(data));

				if ( checkImageType(data) ) {
					str = "<div>"
						+ "<a href='displayFile?fileName=" + getImageLink(data) + "'>"
						+ "<img src='displayFile?fileName=" + data + "'/>"
						+ "</a><small data-src=" + data + ">X</small></div>";
				} else {
					str = "<div><a href='displayFile?fileName=" + data + "'>"
						+ getOriginalName(data)
						+ "</a><small data-src=" + data + ">X</small></div>";
				}

				$(".uploadedList").append(str);
			}
		});
		/*
			processData : 데이터를 일반적인 query String으로 변환할 것인지를 결정, 기본값은 true로, "application/x-www-form-urlencoded" 타입으로 전송한다.
						  다른 형식으로 데이터를 보내기 위해서 자동 변환하고 싶지 않은 경우는 false를 지정한다.
			contentType : 기본값은 "application/x-www-form-urlencoded". 파일의 경우 multipart/form-data 방식으로 전송하기 위해서 false로 지정한다.
		*/
	});

	$(".uploadedList").on("click", "small", function(event){

		var that = $(this);

		$.ajax({
			url : "deleteFile",
			type : "POST",
			data : {fileName:$(this).attr("data-src")},
			dataType : "text",
			success : function(result){
				if(result == 'deleted'){
					that.parent("div").remove();
				}
			}
		});

	});
</script>

<%@ include file="include/footer.jsp" %>
