package com.chan.domain;

import lombok.Data;

@Data
public class PagingVO {

	private int pageSize = 10;	//한 페이지당 보여지는 게시물의 수
	private int rangeSize = 10;	//한 블럭당 페이지 수 ( 한번에 보여지는 페이지의 수 )

	private int curPage = 1;	//현재 페이지
	private int curRange = 1;	//현재 블럭

	private int boardCnt;		//총 게시물의 수(boardDAO.boardCnt()를 통해 Controller에서 받아온다)
	private int pageCnt;		//총 페이지의 수
	private int rangeCnt;		//총 블럭의 수

	private int startPage = 1;	//시작 페이지
	private int endPage = 1;	//끝 페이지
	private int startIndex = 0;	//시작 인덱스

	private int prevPage;		//이전 페이지
	private int nextPage;		//다음 페이지

	public PagingVO() {

	}

	public PagingVO(int boardCnt, int curPage) {

		/*
		 * 페이징 처리 순서
		 *  1. 총 페이지의 수
		 *  2. 총 블럭(range)의 수
		 *  3. 블럭(range) setting
		 */

		// Controller에서 생성자를 통해 총 게시물의 수와 현재 페이지를 받아온다.(Controller로부터 curPage의 기본값 1을 받는다.)
		setBoardCnt(boardCnt);
		setCurPage(curPage);

		// 총 페이지의 수 (총 게시물의 수를 넣어서 계산)
		setPageCnt(boardCnt);

		// 총 range의 수 (총 페이지의 수를 넣어서 계산)
		setRangeCnt(pageCnt);

		// 블럭(range) setting
		rangeSetting(curPage);

		// startIndex 설정
		setStartIndex(curPage);
	}

	public void setPageCnt(int boardCnt) {
		//총 페이지의 갯수(pageCnt) = 총 게시물의 수(boardCnt) / 한페이지당 보여지는 게시물의 수(pageSize)
		//페이지는 정수형인데 ceil의 매개변수를 double을 받기 때문에 총 게시물의 수에 1.0을 곱하고 위에서 10으로 설정해둔 pageSize로 나눈다.
		//ex) 총 게시물의 개수가 36개인데 한 페이지당 10개의 게시물을 보여준다면, 총 몇 페이지가 필요할까?
		//	 - 1) 36 / 10 = 3.6
		//   - 2) 3.6을 소수점 첫째자리에서 반올림하면 4
		//   ----> 4페이지가 필요
		this.pageCnt = (int)Math.ceil( boardCnt * 1.0 / pageSize );
	}

	public void setRangeCnt(int pageCnt) {
		//setPageCnt와 계산방법 동일
		this.rangeCnt = (int)Math.ceil( pageCnt * 1.0 / rangeSize );
	}

	public void rangeSetting(int curPage) {

		setCurPage(curPage);
		//한 블럭(range)의 시작페이지 계산
		// 내가 23번째 페이지의 글을 보려고 한다면, 현재 블럭은 1~10, 11~20, 21~30이기 때문에 3번째 블럭(range)에 있어야 한다.
		// 이 블럭(3번째 블럭)의 startPage를 구하려면...
		// (3(현재블럭) - 1) * 10 + 1 = 21 계산한 결과와 동일한 값이 나온다.
		this.startPage = (curRange - 1) * rangeSize + 1;
		//끝 페이지는 시작페이지를 구한 값에 rangeSize(10)을 더한 뒤 1을 빼면... 21 + 10 - 1 = 30
		this.endPage   = startPage + rangeSize - 1;

		//만약 끝 페이지가, 총 페이지의 수보다 크다면...
		//위에서 계산한 결과로 36이라는 endPage가 나왔는데 만일 총 pageCnt보다 값이 크다면, 그 이상으로 더 페이지 표시를 할 필요가 없기 때문에
		//endPage에 pageCnt를 대입한다.
		if ( endPage > pageCnt ) {
			this.endPage = pageCnt;
		}

		this.prevPage = startPage - 1;
		this.nextPage = endPage   + 1;
	}

	public void setCurRange(int curPage) {
		//현재 블럭 계산하는 메서드
		//현재 페이지가 15페이지(curPage)라면, range는 1~10, 11~20이기 때문에 2번째 range에 속해있다.
		//ex) 현재 페이지가 20페이지라면...
		//   (int)( (20 - 1) / 10 ) = 1
		//   1 + 1 = 2; (curPage - 1)을 해주지 않으면 3이란 결과가 나온다.
		this.curRange = (int)( (curPage - 1) / rangeSize ) + 1;
	}

	public void setStartIndex(int curPage) {
		//마지막으로, DB에서 데이터를 질의할때 사용될 offset값을 설정한다.
		//현재 페이지에 보여줄 실 게시물 데이터를 위한 offset값으로 현재페이지와 pageSize(한 페이지에 들어갈 게시물 수)로 계산해준다.
		this.startIndex = ( curPage - 1 ) * pageSize;
	}
}