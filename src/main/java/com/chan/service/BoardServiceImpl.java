package com.chan.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chan.domain.BoardVO;
import com.chan.domain.Criteria;
import com.chan.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	/*
	 * 게시물을 등록하는 dao.create()를 호출하고나서, 첨부파일의 이름을 배열을 이용해서 각각의 파일의 이름을 데이터베이스에 추가한다.
	 * @see com.chan.service.BoardService#create(com.chan.domain.BoardVO)
	 */
	@Transactional
	@Override
	public void create(BoardVO boardVO) throws Exception {

		dao.create(boardVO);

		String[] files = boardVO.getFiles();

		if ( files == null )
			return;

		for ( String fileName : files ) {
			dao.addAttach(fileName, boardVO.getBoardId());
		}
	}

	@Override
	public BoardVO read(String boardId) throws Exception {

		return dao.read(boardId);
	}

	@Override
	public void update(BoardVO boardVO) throws Exception {

		dao.update(boardVO);
	}

	@Override
	public void remove(String boardId) throws Exception {

		dao.remove(boardId);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {

		return dao.listAll();
	}

	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {

		return dao.listPage(cri);
	}

	@Override
	public int getTotalDataCnt(Criteria cri) throws Exception {

		return dao.getTotalDataCnt(cri);
	}

}
