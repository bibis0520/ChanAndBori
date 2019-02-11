<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<div class="container padding-t10">

		<form name="regiForm" id="regiForm" class="overflow-a" method="post">

		  <!-- 글쓰는 영역 ----------------------------------------------------------------------------------------------------------------------->
			<div class="form-group">
				<label for="title">Title</label>
				<input class="form-control" type="text" name="title" id="title" placeholder="Enter Title..."/>
			</div>

			<div class="form-group">
				<label for="smartEditor">Content</label>
				<textarea class="form-control" style="width:100%" rows="10" cols="40" name="content" id="smartEditor" placeholder="Enter Content..."></textarea>
			</div>

			<div class="form-group">
				<label for="regiUserId">Writer</label>
				<input class="form-control" type="text" name="regiUserId" id="regiUserId" placeholder="Enter Writer..."/>

			</div>

		  <!-- 파일 업로드 영역 ------------------------------------------------------------------------------------------------------------------->
			<div class="form-group">
				<label for="">File Drop Here!!!</label>
				<div class="fileDrop text-right">
		    		<div id="percent"> 0 % </div>
		    		<div id="status">ready</div>
				</div>
			</div>

			<div>
				<hr />
			</div>

			<ul class="mailbox-attachments clearfix uploadedList">
				<%@ include file="uploadedFiles.jsp" %> 	<!-- uploadedFiles.jsp안에서 upload.js를 include받아서 사용한다. -->
			</ul>

		  <!-- 버튼(등록, 취소) 영역 -------------------------------------------------------------------------------------------------------------->
			<div class="text-center">
				<button type="submit" class="btn btn-outline-primary m5">등록</button>
				<a href="/board/listPage${cri.makeQuery()}" id="btnCancel" class="btn btn-outline-danger m5">취소</a>
			</div>

		</form>

		<form action="/uploadAjaxes" id="form_attach" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="type" value="ajax" />
			<input type="file" name="files" id="ajax-file" style="display: none;"/>
		</form>

	</div><!-- /.container -->

</div><!-- /.contents -->

<script>
$(document).ready(function(){

	naverSmartEditorRegister();

	//등록 버튼
	$("#regiForm").submit(function(event) {

		oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);

		var title = $("#title").val(),
			content = $("#smartEditor").val(),
			regiUserId = $("#regiUserId").val(),
			editorContent = content.replace(/<\/?[a-z][a-z0-9]*[^<>]*>/ig, "");	//	<p>KimChan</p> ===>  KimChan
		console.log("title : " + title + ", content : " + content + ", regiUserId : " + regiUserId + ", editorContent : " + editorContent);

		if ( title == "" ) {
			alert("제목을 입력하세요.");
			document.regiForm.title.focus();
			return;
		}

		if(content == "" || content == null || content == '&nbsp;' || content == '<p>&nbsp;</p>' || content == '<p><br></p>' || editorContent == "" ) {
            alert("내용을 입력하세요.");
            oEditors.getById["smartEditor"].exec("FOCUS"); //포커싱
            return;
       	}

		if ( regiUserId == "" ) {
			alert("작성자를 입력하세요.");
			document.regiForm.regiUserId.focus();
			return;
		}

		document.regiForm.submit();
	});

});
</script>

<%@ include file="../include/footer.jsp" %>