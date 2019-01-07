package com.chan.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class PageMaker {
																				//페이지 번호를 출력하는 기능을 하는 클래스

	// 데이터베이스에서 계산되는 데이터
	private int     totalDataCnt;			// 실제 게시물의 총 개수

	// 계산을 통해 만들어지는 데이터
	private int     startPageNum;			// 현재 페이지 기준으로 시작 페이지 번호
	private int     endPageNum; 			// 현재 페이지 기준으로 끝 페이지 번호
	private boolean prev;					// 이전 버튼 활성화 여부
	private boolean next;					// 다음 버튼 활성화 여부
	private int     realEndPageNum;			// 실제로 존제하는 총 페이지의 수

	private int 	displayPageCnt = 10;	// 한번에 보여지는 페이지 번호의 수

	// 외부에서 입력되는 데이터
	private Criteria cri;					// page(현재 페이지), perPageNum(페이지당 보여질 게시물의 개수)

	// 생성자
	public PageMaker(Criteria cri) {
		this.cri = cri;
	}

	// 데이터베이스에서 계산된 값을 가지고 totalDataCnt에 대입한 뒤 calcData() 메서드를 호출한다.
	public void setTotalDataCnt(int totalDataCnt) {
		this.totalDataCnt = totalDataCnt;
		calcData();
	}

	public void calcData() {
		int page = this.cri.getPage();
		int perPageNum = this.cri.getPerPageNum();

		// 현재 페이지가 34페이지라면...
		// endPageNum은 34 / 10 = 3.4... 올림하면 4, 거기에 10(displayPageCnt)를 곱해주면 40
		// 그리고 endPageNum을 이용해서 startPageNum을 구하면 40 - 10 + 1 = 31이란 startPageNum을 구할 수 있다.
		// 현재 페이지가 2페이지라면...
		// endPageNum은 2 / 10 = 0.2... 올림하면 1, 거기에 10을 곱해주면 10(endPageNum), startPageNum을 구하는 공식은 위와 동일.
		this.endPageNum   = (int)( Math.ceil(page / (double)displayPageCnt) ) * displayPageCnt;
		this.startPageNum = (endPageNum - displayPageCnt) + 1;

		// 실제로 존재하는 페이지의 수는...
		// 게시물이 만일 38개 존재한다면, 한페이지당 10개의 게시물만을 보여줬을때!
		// 38 / 10은 3.8... 3.8을 올림하면 4.
		// 따라서 실질적으로 4개의 페이지만 존재하면 된다.
		realEndPageNum = (int)( Math.ceil(totalDataCnt / (double)perPageNum) );

		// 만약 위에서 계산된 endPageNum보다 실제로 존재해야할 페이지의 수인 realEndPageNum이 작다면!
		// 이전에 계산된 값을 버리고, realEndPageNum을 대입한다.(실제로 4페이지까지만 있으면 되는데 10페이지까지 표시할 이유가 없기 때문에)
		if( this.endPageNum > realEndPageNum ) {
			this.endPageNum = realEndPageNum;
		}

		//만일 startPageNum이 1이라면 이전버튼 비활성화, 반대라면 활성화
		prev = startPageNum == 1 ? false : true;

		//만일 endPageNum * perPageNum이 totalDataCnt보다 크거나 같아진다면, next버튼은 비활성화, 그 반대라면 활성화
		next = endPageNum * perPageNum >= totalDataCnt ? false : true;
	}

	public String makeQuery(int page) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
														  .queryParam("page", page)
														  .queryParam("perPageNum", this.cri.getPerPageNum())
														  .build()
														  .encode();

		return uriComponents.toString();
	}
}
