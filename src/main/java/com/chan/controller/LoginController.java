package com.chan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/*")
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
		logger.info("login...");
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void signup() {
		logger.info("signup...");
	}
}
