package com.chan.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {

	//uri를 통해서 입력받을 값들 = 외부에서 입력받는 값 (/board/listPage?page=3&perPageNum=10)
	private int page;
	private int perPageNum;

	private int startBoardRowNum;
	private int endBoardRowNum;

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	// *** 한번에 보여지는 페이지 중 시작 페이지 번호
	public int getRangeStart() {
		return ( this.page - 1 ) * perPageNum;
	}

	public void setPage(int page) {
		//만일 page가 0이하라면 1로 설정, 아닐경우 그 값을 대입
		if ( page <= 0 ) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}

	public void setPerPageNum(int perPageNum) {
		//만일 perPageNum이 0보다 작거나 100보다 크다면 10으로 설정, 아닐경우 그 값을 대입
		if ( perPageNum <= 0 || perPageNum > 100 ) {
			this.perPageNum = 10;
		} else {
			this.perPageNum = perPageNum;
		}
	}

	public String makeQuery() {

		return UriComponentsBuilder.newInstance()
				                   .queryParam("page", page)
				                   .queryParam("perPageNum", perPageNum)
				                   .build()
				                   .encode()
				                   .toString();
	}
}
