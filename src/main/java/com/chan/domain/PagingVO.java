package com.chan.domain;

import lombok.Data;

@Data
public class PagingVO {

	private int pageCnt;		// 한번에 출력할 페이지번호 개수 (1페이지당 10개의 게시물을 조회할 예정)
	private int index;			// 페이지의 번호 1, 2, 3페이지,,,
	private int pageStartIndex;	// 페이지 시작 번호 1,,,,10에서 1

	private int totalCount;		// 게시물의 총 개수
	private int listCnt;		// 한 페이지에 조회할 게시물의 개수

	// 기본 생성자
	public PagingVO() {
		// 한 번에 10페이지 까지 출력되며, 시작 페이지의 index는 1, 그리고 한 페이지당 10개씩 게시물이 출력된다.
		this.pageCnt = 10;
		this.index = 0;
		this.pageStartIndex = 1;
		this.listCnt = 10;
	}

	// 페이지의 첫 번호 얻어오기
	public int getStart() {
		return (index * pageStartIndex) + 1;
	}

	// 페이지의 끝 번호 얻어오기
	public int getLast() {
		return (index * pageStartIndex) + listCnt;
	}


}
