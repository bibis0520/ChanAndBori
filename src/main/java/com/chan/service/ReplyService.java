package com.chan.service;

import java.util.List;

import com.chan.domain.ReplyVO;

public interface ReplyService {

	List<ReplyVO> list(String boardId) throws Exception;

	void create(ReplyVO vo) throws Exception;

	void update(ReplyVO vo) throws Exception;

	void delete(Integer rno) throws Exception;

}
