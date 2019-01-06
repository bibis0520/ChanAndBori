package com.chan.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {

	//uri를 통해서 입력받을 값들 = 외부에서 입력받는 값 (/board/listPage?page=3&perPageNum=10)
	private int page;
	private int perPageNum;

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getRangeStart() {
		return ( this.page - 1 ) * perPageNum;
	}

	public void setPage(int page) {
		if ( page <= 0 ) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}

	public void setPerPageNum(int perPageNum) {
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
