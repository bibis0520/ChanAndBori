package com.chan.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chan.domain.ReplyVO;
import com.chan.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Inject
	private ReplyDAO dao;

	@Override
	public List<ReplyVO> list(String boardId) throws Exception {

		return dao.list(boardId);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {

		dao.create(vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {

		dao.update(vo);
	}

	@Override
	public void delete(Integer rno) throws Exception {

		dao.delete(rno);
	}

}
