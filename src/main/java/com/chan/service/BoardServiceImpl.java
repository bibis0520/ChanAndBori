package com.chan.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chan.domain.BoardVO;
import com.chan.persistence.BoardDAO;
import com.chan.search.BoardSO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	@Override
	public void create(BoardVO boardVO) throws Exception {

		dao.create(boardVO);
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
	public List<BoardVO> listPage(BoardSO boardSO) throws Exception {

		return dao.listPage(boardSO);
	}

	@Override
	public int getTotalBoardCnt() throws Exception {

		return dao.getTotalBoardCnt();
	}

}
