package com.chan.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.chan.domain.TestVO;
import com.chan.domain.search.TestSO;

public class TestDAOImpl implements TestDAO {

	@Inject
	private SqlSession session;

	private static final String NAMESPACE = "com.chan.persistence.testDAO";
	private static final String SELECT_BOARD_LIST = NAMESPACE + ".selectBoardList";
	private static final String SELECT_BOARD_LIST_TOTAL_COUNT = NAMESPACE + ".selectBoardListTotalCount";
	private static final String INSERT_BOARD = NAMESPACE + ".insertBoard";

	@Override
	public List<TestVO> selectBoardList(TestSO searchVo) {

		return session.selectList(SELECT_BOARD_LIST);
	}

	@Override
	public int selectBoardListTotalCount(TestSO searchVo) {

		return session.selectOne(SELECT_BOARD_LIST_TOTAL_COUNT, searchVo);
	}

	@Override
	public void insertBoard(TestVO vo) {

		session.insert(INSERT_BOARD, vo);
	}

}
