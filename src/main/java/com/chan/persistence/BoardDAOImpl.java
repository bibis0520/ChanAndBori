package com.chan.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.chan.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session;
	
	private static final String NAMESPACE = "BoardMapper";
	private static final String LISTPAGE = NAMESPACE + ".listPage";
	private static final String CREATE = NAMESPACE + ".create";
	
	@Override
	public List<BoardVO> listPage() throws Exception {
		
		return session.selectList(LISTPAGE);
	}
	
	@Override
	public void create(BoardVO vo) throws Exception {

		session.insert(CREATE, vo);
	}


}
