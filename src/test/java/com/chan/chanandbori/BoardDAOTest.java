package com.chan.chanandbori;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chan.domain.BoardVO;
import com.chan.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"file:src/main/webapp/WEB-INF/spring/**/chanSpringContext.xml"})
public class BoardDAOTest {

	@Inject
	private BoardDAO dao;

	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);


	@Ignore @Test
	public void testCreate() throws Exception{

		BoardVO vo = new BoardVO();

		vo.setTitle("123");
		vo.setContent("1234");
		vo.setRegiUserId("123345");

		logger.info(vo.toString());	// INFO : com.chan.chanandbori.BoardDAOTest - BoardVO(bno=null, title=TestTitle, content=TestContent, writer=TestWriter, regdate=null, viewcnt=0)

		dao.create(vo);
	}

	@Ignore @Test
	public void testUpdate() throws Exception{
		BoardVO vo = new BoardVO();

		vo.setBno(404);
		vo.setTitle("수정테스트");
		vo.setContent("수정 콘텐츠");

		dao.update(vo);
	}

	@Ignore @Test
	public void testDelete() throws Exception{

		String boardId = "2019010719321400604GYGWVFXMPXZ";
		dao.remove(boardId);
	}

	@Test
	public void testGetTotalDataCnt() throws Exception{

		int totalCount = dao.getTotalBoardCnt();

		System.out.println("totalCount 테스트 결과 : " + totalCount);
	}

}
