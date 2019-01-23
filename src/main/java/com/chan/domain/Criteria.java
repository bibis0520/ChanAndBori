package com.chan.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {

	//uri를 통해서 입력받을 값들 = 외부에서 입력받는 값 (/board/listPage?pageNum=3&perPageNum=10)
	private int pageNum;
	private int perPageNum;

	//for 검색처리를 위해서 추가
	private String searchType;
	private String keyword;

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Criteria() {
		this.pageNum = 1;
		this.perPageNum = 10;
	}

	public void setPage(int pageNum) {
		//만일 pageNum가 0이하라면 1로 설정, 아닐경우 그 값을 대입
		if ( pageNum <= 0 ) {
			this.pageNum = 1;
			return;
		}
		this.pageNum = pageNum;
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
				                   .queryParam("pageNum", this.pageNum)
				                   .queryParam("perPageNum", this.getPerPageNum())
				                   .queryParam("keyword", this.getKeyword())
				                   .queryParam("searchType", this.getSearchType())
				                   .build().encode().toString();
	}

}