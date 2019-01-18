package com.chan.persistence;

import java.util.List;

import com.chan.domain.BoardVO;
import com.chan.search.BoardSO;

public interface BoardDAO {

	void create(BoardVO boardVO) throws Exception;

	BoardVO read(String boardId) throws Exception;

	void update(BoardVO boardVO) throws Exception;

	void remove(String boardId) throws Exception;

	List<BoardVO> listAll() throws Exception;

	List<BoardVO> listPage(BoardSO boardSO) throws Exception;

	int getTotalBoardCnt() throws Exception;

}
