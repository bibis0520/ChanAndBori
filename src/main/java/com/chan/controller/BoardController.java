package com.chan.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chan.domain.BoardVO;
import com.chan.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService service;

// listPage
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(Model model) throws Exception{

		logger.info("listPageGET......Show Board's List");
//		List<BoardVO> rtn = service.listPage();
//		System.out.println("");
//		System.out.println(rtn);
//		for(BoardVO p : rtn) {
//			System.out.println(ToStringBuilder.reflectionToString(p,ToStringStyle.MULTI_LINE_STYLE));
//		}
//		System.out.println("");
		model.addAttribute("list", service.listPage());
	}

// register(CREATE)
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO boardVO, Model model) throws Exception{

		logger.info("registerGET......");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{

		logger.info("registerPOST......");

	    service.create(vo);

	    return "redirect:/board/listPage";
	}


	@RequestMapping(value = "/viewRead", method = RequestMethod.POST)
	public ModelAndView viewRead(@ModelAttribute BoardVO boardVo) throws Exception{
		logger.info("viewRead ...");

		ModelAndView view = new ModelAndView("/board/read");

		view.addObject("boardVo", service.read(boardVo));

		return view;
	}

//	@RequestMapping(value = "/read", method = RequestMethod.GET)
//	public void viewRead(@RequestParam("bno") Integer bno, Model model) throws Exception{
//		logger.info("readGET ...");
//
//		model.addAttribute(service.read(bno));
//	}
}
