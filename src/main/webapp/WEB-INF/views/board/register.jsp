<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<div class="container padding-t10">
<!-- 글쓰는 영역 -->
		<form name="regiForm" class="overflow-a" method="post">
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
		</form>

<!-- 파일 업로드 영역 -->
		<div class="form-group">
			<label for="exampleInputEmail1">File Drop Here</label>
			<div class="fileDrop"></div>
		</div>

		<div>
			<hr />
		</div>

		<ul class="mailbox-attachments clearfix uploadedList"></ul>

<!-- 버튼(등록, 취소) 영역 -->
		<div class="text-center">
			<button type="button" id="btnRegister"  class="btn btn-outline-primary m5">등록</button>
			<a href="/board/listPage${cri.makeQuery()}" id="btnCancel" class="btn btn-outline-danger m5">취소</a>
		</div>

	</div><!-- /.container -->

</div><!-- /.contents -->

<style>
.fileDrop{
	width:80%;
	height:100px;
	border:5px dotted whitesmoke;
	background-color:lightslategrey;
	margin:auto;
}
</style>

<script type="text/javascript" src="/resources/js/upload.js"></script>
<script id="template" type="text/x-handlebars-template">
	<li>
		<span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
		<div class="mailbox-attachment-info">
			<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
			<a href="{{fullName}}" class="btn btn-default btn-xs pull-rignt delbtn"><i class="fa fa-fw fa-remove"></i></a>
		</div>
	</li>
</script>
<script>
$(document).ready(function(){

	naverSmartEditorRegister();

	var template = Handlebars.compile($("#template").html());

	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});

	$(".fileDrop").on("drop", function(event){
		event.preventDefault();
		var files = event.originalEvent.dataTransfer.files;

		var file = files[0];

		var formData = new FormData();

		formData.append("file", file);

		$.ajax({
			url : "/uploadAjax",
			data : formData,
			dataType : "text",
			processData : false,
			contentType : false,
			type : "POST",
			success : function(data){
				var fileInfo = getFileInfo(data);
				var html = template(fileInfo);

				console.log("fileInfo : " + fileInfo + ", html : " + html);

				$(".uploadedList").append(html);
			}
		});

	});

	//등록 버튼
	$("#btnRegister").on("click", () => {
		/* event.preventDefault();

		var that = $(this);
		var str = "";

		$(".uploadedList .delbtn").each(function(index){
			str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "'>";
		});

		that.append(str);
		that.get(0).submit(); */

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