package com.chan.service;

import java.util.List;

import com.chan.domain.BoardVO;
import com.chan.domain.Criteria;

public interface BoardService {

	// CRUD
	void create(BoardVO boardVO) throws Exception;

	BoardVO read(String boardId) throws Exception;

	void update(BoardVO boardVO) throws Exception;

	void remove(String boardId) throws Exception;

	// LIST
	List<BoardVO> listAll() throws Exception;

	List<BoardVO> listPage(Criteria cri) throws Exception;

	// GET_DATA
	int getTotalDataCnt() throws Exception;

}
