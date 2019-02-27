package com.chan.persistence;

import java.util.List;

import com.chan.domain.ReplyVO;

public interface ReplyDAO {

	// 댓글 조회( boardId를 가지고 해당 게시물의 댓글을 list형식으로 조회해서 return )
	public List<ReplyVO> list(String boardId) throws Exception;

	// 댓글 등록
	public void create(ReplyVO vo) throws Exception;

	// 댓글 수정( vo에 담긴 rno로 해당 댓글을 선택하고, replyText에 담긴 내용으로 기존의 내용을 update한다. )
	public void update(ReplyVO vo) throws Exception;

	// 댓글 삭제( 해당 게시물의 댓글 번호를 가지고 해당 게시물의 댓글을 삭제 )
	public void delete(Integer rno) throws Exception;
}
