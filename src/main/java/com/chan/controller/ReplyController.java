package com.chan.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chan.domain.ReplyVO;
import com.chan.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@Inject
	private ReplyService service;

//댓글 등록 ( /replies/ + json데이터, POST방식, 전송된 데이터를 객체화시키기 위해서 @RequestBody 어노테이션을 사용한다. )
	@RequestMapping( value = "", method = RequestMethod.POST )
	public ResponseEntity<String> register ( @RequestBody ReplyVO vo ) throws Exception {
		// @RequestBody를 이용해서 전송된 데이터(댓글 내용)를 json데이터로 변환 
		
		logger.info("댓글 내용 ---> {}", vo);
		ResponseEntity<String> entity = null;
		
		try {
			
			service.create(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
//댓글 조회 ( /all/ + 2019030811380000042GLICQGJJNSX, 해당 게시물의 PK인 BOARD_ID를 가지고 등록된 모든 댓글을 조회한다. )
	@RequestMapping( value = "/all/{boardId}", method = RequestMethod.GET )
	public ResponseEntity<List<ReplyVO>> list( @PathVariable("boardId") String boardId ) throws Exception {
		
		logger.info("댓글 리스트 조회!");
		
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			List<ReplyVO> list = service.list(boardId);	// @PathVariable를 통해 uri에서 필요한 값을 가져와 List객체에 댓글 목록을 담는다.
			entity = new ResponseEntity<>(list, HttpStatus.OK);	// 그리고 list를 ResponseEntity에 담아 return
			
		} catch ( Exception e ) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
//댓글 수정 ( /8, 댓글 수정은 댓글 번호(rno)를 가지고 수정한다. method는 PUT & PATCH를 모두 사용하도록 선언 )
	@RequestMapping( value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH } )
	public ResponseEntity<String> update ( @PathVariable("rno") Integer rno,
										   @RequestBody ReplyVO vo ) throws Exception {
		// @PathVariable에 의해 수정할 댓글 번호인 rno를 받고, 수정 내용을 json데이터로 변환시켜야 하기 때문에 @RequestBody를 사용한다. 
		logger.info( rno + "번 댓글을 수정!");
		
		ResponseEntity<String> entity = null;
		
		try {
			vo.setRno(rno);
			service.update(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			
		} catch ( Exception e ) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
