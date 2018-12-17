package com.chan.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chan.domain.TestVO;
import com.chan.domain.search.TestSO;

@Transactional
public interface TestService {
	/**
	 *  Method Name : getBoardList
	 *  Description : 게시물 목록 조회 서비스 인터페이스
     *
	 *  @param TestSO 게시물 검색조건
	 *  @return List<TestVO> 게시판 목록데이터 반환
	 */
	List<TestVO> getBoardList(TestSO searchVo);

	/**
	 *  Method Name : getBoardListTotalCount
	 *  Description : 게시물 전체 목록 개수  서비스 인터페이스
	 *
	 *  @param TestSO 게시물 검색조건
	 *  @return int 게시판 전체 목록 개수 반환
	 */
	int getBoardListTotalCount(TestSO searchVo);

	/**
	 *  Method Name : saveBoard
	 *  Description : 게시물 저장 서비스 인터페이스
	 *
	 *  @param TestVO 게시물 정보
	 */
	void saveBoard(TestVO saveVo);
}
