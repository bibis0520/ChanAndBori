package com.chan.persistence;

import java.util.List;

import com.chan.domain.BoardVO;

public interface BoardDAO {

	public List<BoardVO> listPage() throws Exception;	// 게시물 리스트 조회

	public void create(BoardVO vo) throws Exception;	// 게시물 등록

	public BoardVO read(BoardVO searchVo) throws Exception;	// 게시물 조회

	public void update(BoardVO vo) throws Exception;	// 게시물 수정

	public void delete(Integer bno) throws Exception;	// 게시물 삭제

}
