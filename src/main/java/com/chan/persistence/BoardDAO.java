package com.chan.persistence;

import java.util.List;

import com.chan.domain.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> listPage() throws Exception;
	
	public void create(BoardVO vo) throws Exception;
	
}
