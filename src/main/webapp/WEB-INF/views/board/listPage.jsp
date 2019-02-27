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
	  		<input class="col-md-6 mr5" type="text" id="keyword" name="keyword" value="${pageMaker.cri.keyword}" placeholder="검색어를 입력하세요..."/>
	  		<button class="btn btn-success" type="button" id="searchBtn">Search</button>
		</div>

		<div>
			<a href="/board/register${pageMaker.makeQuery(pageMaker.cri.pageNum)}">
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
				<td><a href="/board/read${pageMaker.makeQuery(pageMaker.cri.pageNum)}&boardId=${boardVO.boardId}">${boardVO.title}</a></td>
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
	      <c:if test="${pageMaker.prev}">
	    	<li class="page-item" id="page-prev">
	      		<a class="page-link" href="listPage${pageMaker.makeQuery(pageMaker.startRangeNum -1)}">&laquo;</a>
	      		<span class="sr-only">Previous</span>
	    	</li>
		  </c:if>

			<c:forEach begin="${pageMaker.startRangeNum}" end="${pageMaker.endRangeNum}" var="idx">
		    	<li <c:out value="${pageMaker.cri.pageNum == idx ? 'class=active' : '' }"/> >
		      		<a class="page-link" href="listPage${pageMaker.makeQuery(idx)}">${idx}<span class="sr-only">(current)</span></a>
		    	</li>
		    </c:forEach>

			<!-- 다음 버튼 -->
		  <c:if test="${pageMaker.next}">
	    	<li class="page-item" id="page-next">
	      		<a class="page-link" href="listPage${pageMaker.makeQuery(pageMaker.endRangeNum + 1)}">&raquo;</a>
	      		<span class="sr-only">Next</span>
	    	</li>
	      </c:if>
	  	</ul>
	</nav>


</div>

<script>
$( document ).ready( function() {

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

	var thisPageNum = "${pageMaker.cri.pageNum}";
	/* console.log(thisPageNum); */
	// 현재 페이지를 가져와서 어느 페이지를 보고있는지 알려준다.
	$("#" + thisPageNum + "pageNum").addClass("active");

	// 테이블의 해당 행을 클릭하면 해당 게시물의 조회(read)페이지로 이동
	$(".boardRow").on('click', function(){
		var boardId = $(this).data("boardId");
		console.log(boardId);
																//pageMaker.cri에 들어가있는 page에 대입되어있는 값을 가지고 makeQuery
		window.location.href = "/board/read${pageMaker.makeQuery(pageMaker.cri.pageNum)}&boardId=" + boardId;
	});

	// 10개씩, 30개씩...등을 처리하기위한 함수.
	function setPerPageNumSelect(){
		var perPageNum 			= "${pageMaker.cri.perPageNum}";
		var	$perPageNumSelect 	= $("#perPageNumSelect");
		var pageNum 			= "${pageMaker.cri.pageNum}";
										//selected란 이름에 true란 속성값을 추가한다.
		$perPageNumSelect.val(perPageNum).prop("selected", true);
		$perPageNumSelect.on("change", function(){

			window.location.href = "listPage?pageNum=" + pageNum + "&perPageNum=" + $perPageNumSelect.val();
		});
	};

	$("#searchBtn").on("click", function(event){

		/* var searchType = $("#searchType").val();						// "w" */
		var searchType = $("select option:selected").val();				// "w"
		/* var searchKeyword = $("#searchKeyword").val();				// "김찬" */
		var searchKeyword = encodeURIComponent($("#keyword").val());

		console.log("searchType : " + searchType + ", searchKeyword : " + searchKeyword);

		if ( searchType == 'n' ) {
			alert("검색조건을 선택해주세요.");
		}

		if ( searchKeyword == '' ) {
			alert("검색어를 입력해주세요.");
			$("#keyword").focus();
		}

		// 버튼을 누를 때의 searchType과 keyword를 가져와 uri를 만들기 때문에
		// pageMaker의 makeQuery를 사용할때 pageNum을 1로 주고, boolean형의 매개변수를 false로 준다.
		var url = "listPage" + "${pageMaker.makeQuery(1, false)}" + "&searchType=" + searchType + "&keyword=" + searchKeyword;
		console.log(url);

		window.location.href = url;

	});
});
</script>

<%@ include file="../include/footer.jsp" %>