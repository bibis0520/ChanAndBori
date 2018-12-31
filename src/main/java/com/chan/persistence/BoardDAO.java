package com.chan.persistence;

import java.util.List;

import com.chan.domain.BoardVO;

public interface BoardDAO {

	List<BoardVO> listPage() throws Exception;			// 게시물 리스트 조회

	void create(BoardVO vo) throws Exception;			// 게시물 등록

	BoardVO read(BoardVO searchVo) throws Exception;	// 게시물 조회

	void update(BoardVO vo) throws Exception;			// 게시물 수정

	void delete(Integer bno) throws Exception;			// 게시물 삭제

}
