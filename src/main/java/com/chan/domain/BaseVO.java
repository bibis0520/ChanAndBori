package com.chan.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BaseVO {
	//모든 도메인 객체에서 공통적으로 가지고 있는 것들...

	private String 	regiUserId;		// 등록사용자ID
	private Date	regiDate;		// 등록일시
	private String 	modiUserId;		// 수정사용자ID
	private Date 	modiDate;		// 수정일시

	private Integer rnum;			// 페이징 처리시 게시물에 붙는 rownum(가상의 열)
	private Integer totalBoardCnt;	// 총 게시물의 수

	private int		pageNum;		// 페이지 번호
	private int 	perPageNum;		// 한 페이지당 보여지는 게시물의 수

	public void setPageNum(int pageNum) {
		if(pageNum <= 0) {
			this.pageNum = 1;
			return;

		} else {

			this.pageNum = pageNum;
		}

	}

	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;

		} else {

			this.perPageNum = perPageNum;
		}
	}

}