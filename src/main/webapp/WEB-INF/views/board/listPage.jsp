<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>

<div class="contents overflow-h padding-b40">

	<div id="registerOK" class="alert alert-success hidden text-center" role="alert">새글이 등록되었습니다.</div>
	<div id="removeOK" class="alert alert-danger hidden text-center" role="alert">글이 삭제되었습니다.</div>

	<div class="m20 overflow-a">

		<div class="input-group col-md-4 padding-0 fl">
	  		<select class="custom-select mr5" id="searchType">
			    <option value="n" 	<c:out value="${cri.searchType == null  ? 'selected' : '' }"/>>---</option>
			    <option value="t" 	<c:out value="${cri.searchType eq 't'   ? 'selected' : '' }"/>>Title</option>
			    <option value="c" 	<c:out value="${cri.searchType eq 'c'   ? 'selected' : '' }"/>>Content</option>
			    <option value="w" 	<c:out value="${cri.searchType eq 'w'   ? 'selected' : '' }"/>>Writer</option>
			    <option value="tc"	<c:out value="${cri.searchType eq 'tc'  ? 'selected' : '' }"/>>Title or Content</option>
			    <option value="cw"	<c:out value="${cri.searchType eq 'cw'  ? 'selected' : '' }"/>>Content or Writer</option>
			    <option value="tcw" <c:out value="${cri.searchType eq 'tcw' ? 'selected' : '' }"/>>Title or Content or Writer</option>
	  		</select>
	  		<input class="col-md-6 mr5" type="text" id="searchKeyword" value="${cri.keyword}"/>
	  		<button class="btn btn-success" type="button" id="searchBtn">Search</button>
		</div>

		<div>
			<a href="/board/register${pageMaker.makeQuery(pageMaker.cri.page)}">
				<button class="btn btn-primary fr">새글등록</button>
			</a>
		</div>

		<div class="input-group col-md-1 fr">
		  	<select class="custom-select" id="perPageNumSelect" aria-label="Example select with button addon">
			    <option value="10">10개씩</option>
			    <option value="30">30개씩</option>
			    <option value="50">50개씩</option>
			    <option value="100">100개씩</option>
		  	</select>
		</div>
	</div>

	<div class="container-fluid">
		<table class="table table-bordered table-hover">
		  <thead>
			<tr>
				<th scope="col" style="width: 10%">BNO</th>
				<th scope="col" style="width: 40%">TITLE</th>
				<th scope="col" style="width: 30%">WRITER</th>
				<th scope="col" style="width: 15%">REGDATE</th>
				<th scope="col" style="width: 5%">VIEWCNT</th>
			</tr>
		  </thead>
		  <tbody>
		  <c:forEach items="${list}" var="boardVO">
			<tr class="boardRow" data-board-id="${boardVO.boardId}">
				<th scope="row" class="bno">${boardVO.bno}</th>
				<td><a href="/board/read${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${boardVO.bno}">${boardVO.title}</a></td>
				<td>${boardVO.regiUserId}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regiDate}"/></td>
				<td class="text-center"><span class="badge badge-pill badge-dark">${boardVO.viewCnt}</span></td>
			</tr>
		  </c:forEach>
		  </tbody>
		</table>
	</div>

	<nav aria-label="pagination">
	  	<ul class="pagination justify-content-center">
	    	<!-- 이전 버튼 -->
	    	<li class="page-item" id="page-prev">
	      		<a class="page-link" href="listPage${pageMaker.makeQuery(pageMaker.startPageNum -1)}">&laquo;</a>
	      		<span class="sr-only">Previous</span>
	    	</li>

			<c:forEach begin="${pageMaker.startPageNum}" end="${pageMaker.endPageNum}" var="idx">
		    	<li class="page-item" id="${idx}page">
		      		<a class="page-link" href="listPage${pageMaker.makeQuery(idx)}">
		        		${idx}<span class="sr-only">(current)</span>
		      		</a>
		    	</li>
		    </c:forEach>

			<!-- 다음 버튼 -->
	    	<li class="page-item" id="page-next">
	      		<a class="page-link" href="listPage${pageMaker.makeQuery(pageMaker.endPageNum + 1)}">&raquo;</a>
	      		<span class="sr-only">Next</span>
	    	</li>
	  	</ul>
	</nav>


</div>

<script>
$(document).ready(function(){

	setPerPageNumSelect();

	var result = '${result}';
	$(function(){
		if(result === "Register Success!!!") {
			$("#registerOK").removeClass("hidden");
			$("#registerOK").fadeOut(3000);
		}
		if(result === "Remove Success!!!") {
			$("#removeOK").removeClass("hidden");
			$("#removeOK").fadeOut(3000);
		}
	});

	// 1~10페이지에서는 prev버튼이 필요없기때문에 hidden처리
	var thisPage = "${pageMaker.cri.page}";
	if ( thisPage <= 10 )
		$("#page-prev").addClass("hidden");

	// 가장 마지막 페이지가 해당된 블록(range)에서는 next버튼이 필요 없기 때문에 hidden처리
	var realEndPageNum = "${pageMaker.realEndPageNum}";
	var lastPagesStartNum = realEndPageNum - (realEndPageNum % 10) + 1;
	if ( thisPage >= lastPagesStartNum && thisPage <= realEndPageNum ) {
		$("#page-next").addClass("hidden");
	}

	// 현재 페이지를 가져와서 어느 페이지를 보고있는지 알려준다.
	var thisPage = "${pageMaker.cri.page}";
	$("#" + thisPage + "page").addClass("active");

	// 테이블의 해당 행을 클릭하면 해당 게시물의 조회(read)페이지로 이동
	$(".boardRow").on('click', function(){
		var bno = $(this).children(".bno").text();
																//pageMaker.cri에 들어가있는 page에 대입되어있는 값을 가지고 makeQuery
		window.location.href = "/board/read${pageMaker.makeQuery(pageMaker.cri.page)}&bno=" + bno;
	});

	// 10개씩, 30개씩...등을 처리하기위한 함수.
	function setPerPageNumSelect(){
		var perPageNum 			= "${pageMaker.cri.perPageNum}";
		var	$perPageNumSelect 	= $("#perPageNumSelect");
		var thisPage 			= "${pageMaker.cri.page}";
										//selected란 이름에 true란 속성값을 추가한다.
		$perPageNumSelect.val(perPageNum).prop("selected", true);

		$perPageNumSelect.on("change", function(){
			/* window.location.href = "listPage?page=1&perPageNum=" + $perPageNumSelect.val(); */
			window.location.href = "listPage?page=" + thisPage +"&perPageNum=" + $perPageNumSelect.val();
		});
	};

});
</script>
<%@ include file="../include/footer.jsp" %>