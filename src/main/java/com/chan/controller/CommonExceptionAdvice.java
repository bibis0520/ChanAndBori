package com.chan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice를 추가함으로서 이 클래스의 객체가 컨트롤러에서 발생하는 Exception을 전문적으로 처리하는 클래스라는 것을 명시.
@ControllerAdvice
public class CommonExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView errorModelAndView(Exception e) {

		logger.info(e.toString());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error_common");
		modelAndView.addObject("exception", e);

		return modelAndView;
	}
}
