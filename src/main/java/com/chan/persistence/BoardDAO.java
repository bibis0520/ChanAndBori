package com.chan.persistence;

import java.util.List;

import com.chan.domain.BoardVO;
import com.chan.domain.Criteria;

public interface BoardDAO {

	List<BoardVO> listAll() throws Exception;						// 게시물 전체 리스트 조회

	List<BoardVO> listPage(Criteria cri) throws Exception; 			// 페이징 처리된 게시물 리스트 조회

	int getTotalDataCnt(Criteria cri) throws Exception;							//총 게시물의 갯수

	void create(BoardVO vo) throws Exception;						// 게시물 등록

	BoardVO read(Integer bno) throws Exception;						// 게시물 조회

	void update(BoardVO vo) throws Exception;						// 게시물 수정

	void remove(Integer bno) throws Exception;						// 게시물 삭제

}
