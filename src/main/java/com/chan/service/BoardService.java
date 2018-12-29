package com.chan.service;

import java.util.List;

import com.chan.domain.BoardVO;

public interface BoardService {

	List<BoardVO> listPage() throws Exception;
	
	void create(BoardVO vo) throws Exception;
	
	BoardVO read(Integer bno) throws Exception;
}
