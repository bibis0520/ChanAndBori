<%@ page import="org.springframework.util.ReflectionUtils"%>
<%@ page import="java.lang.reflect.Method"%>
<%@ page import="com.chan.domain.BaseVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.lang.Object"%>

<%  /* 페이징 처리에 필요한 지역변수들을 선언하는 곳, 이곳에선 페이징 처리를 위해 필요한 값들을 계산!!! */

	int pageNum 		= 0;
	int perPageNum 		= 0;

	int startRangeNum 	= 0; 		// 현재 페이지 기준으로 만들어지는 페이징 범위의 시작 번호
	int endRangeNum 	= 0; 		// 현재 페이지 기준으로 만들어지는 페이징 범위의 끝 번호

	boolean prev  		= false; 	// 이전 버튼 활성화 여부
	boolean next 		= false; 	// 다음 버튼 활성화 여부

	int totalPageCnt 	= 0; 		// 실제로 존재하는 총 페이지의 수
	int totalBoardCnt 	= 0; 		// 실제로 존재하는 총 게시물의 수

	int displayPageCnt 	= 10; 		// 한번에 보여지는 페이지 번호의 수

	List<? extends BaseVO> list = (ArrayList<? extends BaseVO>)request.getAttribute("list");

	if (list != null && list.size() > 0) {

		Object firstRow = (Object)list.get(0);
		Class<?> clazz = firstRow.getClass();

		Method getPageNum 		= ReflectionUtils.findMethod(clazz, "getPageNum");
		Method getPerPageNum 	= ReflectionUtils.findMethod(clazz, "getPerPageNum");
		Method getTotalBoardCnt = ReflectionUtils.findMethod(clazz, "getTotalBoardCnt");

		pageNum 		= (int) ReflectionUtils.invokeMethod(getPageNum, firstRow);
		perPageNum 		= (int) ReflectionUtils.invokeMethod(getPerPageNum, firstRow);
		totalBoardCnt 	= (int) ReflectionUtils.invokeMethod(getTotalBoardCnt, firstRow);

		endRangeNum 	= (int) (Math.ceil(pageNum / (double) displayPageCnt)) * displayPageCnt;
		startRangeNum 	= (endRangeNum - displayPageCnt) + 1;

		totalPageCnt = (int) (Math.ceil(totalBoardCnt / (double) perPageNum));

		if (endRangeNum > totalPageCnt) {
			endRangeNum = totalPageCnt;
		}

		prev = startRangeNum == 1 ? false : true;
		next = endRangeNum * perPageNum >= totalBoardCnt ? false : true;

	} else {
		/*검색결과없을때 처리*/
	}
%>