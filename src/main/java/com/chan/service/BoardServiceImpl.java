package com.chan.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chan.domain.BoardVO;
import com.chan.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
	
	@Override
	public List<BoardVO> listAll() throws Exception {

		return dao.listAll();
	}

	@Override
	public void create(BoardVO vo) throws Exception {

		dao.create(vo);
	}

}