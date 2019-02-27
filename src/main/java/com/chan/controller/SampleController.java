package com.chan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chan.domain.SampleVO;

@RestController
@RequestMapping("/sample")
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

	@RequestMapping("/hello")
	public String sayHello() {

		return "Hello World";	// text/html
	}

	@RequestMapping("/sendVO")
	public SampleVO sendVO() {

		SampleVO vo = new SampleVO();
		vo.setMno(1);
		vo.setFirstName("Chan");
		vo.setLastName("Kim");		// 브라우저에서 다음과 같이 보여진다. {"mno":1,"firstName":"Chan","lastName":"Kim"} (Json형식)

		return vo;	// Content-Type: application/json;charset=UTF-8 (브라우저의 검사창에서 확인 가능, pom.xml에 jackson-databind라이브러리를 추가해서 사용 가능)
	}

	@RequestMapping("/sendList")
	public List<SampleVO> sendList(){

		List<SampleVO> list = new ArrayList<>();

		for ( int i = 0; i < 10; i++ ) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName(i + "'s FirstName");
			vo.setLastName(i + "'s LastName");

			list.add(vo);
		}

		return list;	//JSON의 문법상 리스트는 배열로 표현

	}

	@RequestMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap(){

		Map<Integer, SampleVO> map = new HashMap<>();

		for ( int i = 0; i < 10; i++ ) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName(i + "'s FirstName");
			vo.setLastName(i + "'s LastName");
			map.put(i, vo);
		}

		return map;
	}

	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendErrorAuth(){

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/sendErrorNot")
	public ResponseEntity< List<SampleVO> > sendErrorNot(){

		List<SampleVO> list = new ArrayList<>();

		for ( int i = 0; i < 10; i++ ) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName(i + "'s FirstName");
			vo.setLastName(i + "'s LastName");

			list.add(vo);
		}

		return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);	// 일반적인 404메세지와는 다르게 전송한 결과는 그대로 보여주면서 상태 보드를 전달한다.
	}

}
