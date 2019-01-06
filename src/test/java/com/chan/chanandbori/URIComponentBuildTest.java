package com.chan.chanandbori;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class URIComponentBuildTest {

	private static final Logger logger = LoggerFactory.getLogger(URIComponentBuildTest.class);

	@Test
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
}