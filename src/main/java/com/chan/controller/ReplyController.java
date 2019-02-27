package com.chan.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chan.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@Inject
	private ReplyService service;

//댓글 등록 ( /replies/ + json데이터, POST방식, 전송된 데이터를 객체화시키기 위해서 @RequestBody 어노테이션을 사용한다. )
//	@RequestMapping( value = "", method = RequestMethod.POST )
//	public ResponseEntity<String> register(@RequestBody ReplyVO vo) throws Exception {
//
//	}
}
