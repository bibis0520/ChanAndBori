package com.chan.chanandbori;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class URIComponentBuildTest {

	private static final Logger logger = LoggerFactory.getLogger(URIComponentBuildTest.class);

	@Ignore @Test
	public void testURIComponent() {
		int page = 6;
		int perPageNum = 10;

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
														  .path("/board/listPage")
														  .queryParam("page", page)
														  .queryParam("perPageNum", perPageNum)
														  .build();

		String UriByMySelf = "/board/listPage?page=" + page + "&perPageNum=" + perPageNum;

		logger.info(UriByMySelf);
		logger.info(uriComponents.toString());

		assertEquals(UriByMySelf, uriComponents.toString());
	}

	@Test
	public void testUriComponent2() {

		int page = 5;
		int perPageNum = 10;
		String searchType = "t";
		String keyword = "한글";

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
														  .path("/board/listPage")
														  .queryParam("page", page)
														  .queryParam("perPageNum", perPageNum)
														  .queryParam("searchType", searchType)
														  .queryParam("keyword", keyword)
														  .build();

		String testedUri = "/board/listPage?page=" + page + "&perPageNum=" + perPageNum
						 + "&searchType=" + searchType + "&keyword=" + keyword;

		logger.info("URI by UriComponentsBulider : " + uriComponents.toString());
		logger.info("URI by Myself 		: " + testedUri);

		assertEquals(testedUri, uriComponents.toString());

//		URI by UriComponentsBulider : /board/listPage?page=5&perPageNum=10&searchType=t&keyword=test
//		URI by Myself 				: /board/listPage?page=5&perPageNum=10&searchType=t&keyword=test 동일한 결과!

	}
}