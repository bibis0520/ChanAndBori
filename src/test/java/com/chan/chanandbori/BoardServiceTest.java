package com.chan.chanandbori;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chan.domain.BoardVO;
import com.chan.domain.Criteria;
import com.chan.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"file:src/main/webapp/WEB-INF/spring/**/chanSpringContext.xml"})
public class BoardServiceTest {

	@Inject
	private BoardService service;

	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

	@Test
	public void testListPage() throws Exception {

		logger.info("BoardService.listPage() Test...");

		Criteria cri = new Criteria();
		cri.setPage(1);
		cri.setPerPageNum(5);

		List<BoardVO> list = service.listPage(cri);

		for(BoardVO board : list)
			logger.info(board.getBno() + " : " + board.getTitle());
	}

}
