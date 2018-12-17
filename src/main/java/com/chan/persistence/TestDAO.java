package com.chan.persistence;

import java.util.List;

import com.chan.domain.TestVO;
import com.chan.domain.search.TestSO;

public interface TestDAO {
	/**
	 *  Method Name : selectBoardList
	 *  Description : 게시물 목록 조회
     *
	 *  @param TestSo 게시물 검색조건
	 *  @return List<TestVO> 게시판 목록데이터 반환
	 */
	List<TestVO> selectBoardList(TestSO searchVo);

	/**
	 *  Method Name : selectBoardListTotalCount
	 *  Description : 게시물 전체 목록 개수 조회
	 *
	 *  @param TestSo 게시물 검색조건
	 *  @return int 게시판 전체 목록 개수 반환
	 */
	int selectBoardListTotalCount(TestSO searchVo);

	/**
	 * Method Name : insertBoard 
	 * Description : 게시물 등록
	 *
	 * @param TestVO 게시물 정보
	 */
	void insertBoard(TestVO vo);
}
