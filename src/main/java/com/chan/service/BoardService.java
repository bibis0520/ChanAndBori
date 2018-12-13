package com.chan.service;

import java.util.List;

import com.chan.domain.BoardVO;

public interface BoardService {

	public List<BoardVO> listPage() throws Exception;
	
	public void create(BoardVO vo) throws Exception;
}
