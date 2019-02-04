package com.chan.persistence;

import java.util.List;

import com.chan.domain.BoardVO;
import com.chan.domain.Criteria;

public interface BoardDAO {

	void create(BoardVO boardVO) throws Exception;

	BoardVO read(String boardId) throws Exception;

	void update(BoardVO boardVO) throws Exception;

	void remove(String boardId) throws Exception;

	List<BoardVO> listAll() throws Exception;

	List<BoardVO> listPage(Criteria cri) throws Exception;

	int getTotalDataCnt(Criteria cri) throws Exception;

	public void addAttach(String fullName, String boardId) throws Exception;

}
