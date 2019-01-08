package com.chan.persistence;

import java.util.List;

import com.chan.domain.BoardVO;
import com.chan.domain.Criteria;

public interface BoardDAO {

	void create(BoardVO vo) throws Exception;

	BoardVO read(Integer bno) throws Exception;

	void update(BoardVO vo) throws Exception;

	void remove(Integer bno) throws Exception;

	List<BoardVO> listAll() throws Exception;

	List<BoardVO> listPage(Criteria cri) throws Exception;

	int getTotalDataCnt(Criteria cri) throws Exception;

}
