package com.chan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chan.domain.TestVO;
import com.chan.domain.search.TestSO;
import com.chan.persistence.TestDAO;
import com.chan.service.TestService;
/**
 *  Class Name : testServiceImpl
 *  Description : 게시판 서비스 로직구현 클래스
 *
 *  @since 2018. 12. 14
 *  @version 1.0
 *  @author hsm0711
 */
@Service("TestService")
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDAO testDao;

	/**
	 *  Method Name : getBoardList
	 *  Description : 게시물 목록 조회 서비스 구현
	 *
	 *  @param TestSo 게시물 검색조건
	 *  @return List<TestVO> 게시판 목록데이터 반환
	 */
	@Override
	public List<TestVO> getBoardList(TestSO searchVo){
		return testDao.selectBoardList(searchVo);
	}

	/**
	 *  Method Name : getBoardListTotalCount
	 *  Description : 게시물 전체 목록 개수  서비스 구현
	 *
	 *  @param TestSO 게시물 검색조건
	 *  @return int 게시판 전체 목록 개수 반환
	 */
	@Override
	public int getBoardListTotalCount(TestSO searchVo){
		return testDao.selectBoardListTotalCount(searchVo);
	}


	/**
	 *  Method Name : saveBoard
	 *  Description : 게시물 등록 서비스 구현
	 *
	 *  @param TestVO 게시물 정보
	 */
	@Override
	public void saveBoard(TestVO saveVo) {
		testDao.insertBoard(saveVo);
	}
}
