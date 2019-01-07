package com.chan.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chan.domain.BoardVO;
import com.chan.domain.Criteria;
import com.chan.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService service;

//  LIST
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAllGET(Model model) throws Exception {

		logger.info("listAllGET......Show Board's List");

		model.addAttribute("list", service.listAll());
	}

	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception {

		List<BoardVO> boards = service.listPage(cri);

		model.addAttribute("list", boards);
	}


//  CREATE
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO boardVO, Model model) throws Exception {

		logger.info("registerGET......");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {

		logger.info("registerPOST......");

	    service.create(vo);
	    rttr.addFlashAttribute("result", "Register Success!!!");

	    return "redirect:/board/listPage";
	}

//  READ
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readGET(@RequestParam("bno") Integer bno, Model model) throws Exception {
		//@RequestParam -> 사용자의 요청(request)에서 특정한 파라미터값을 찾아낼때 사용.
		logger.info("readGET......");

		//URI에서 얻어낸 bno값으로 게시물을 읽어온다.
		model.addAttribute(service.read(bno));
	}

	//	@RequestMapping(value = "/read", method = RequestMethod.POST)
	//	public ModelAndView viewRead(@ModelAttribute BoardVO boardVO) throws Exception {
	//		//@ModelAttribute -> 해당 객체를 뷰까지 자동으로 전달해주는 Annotation
	//		logger.info("viewReadPOST......");
	//
	//		ModelAndView mav = new ModelAndView("/board/read");
	//
	//		mav.addObject("boardVO", service.read(boardVO));
	//
	//		return mav;
	//	}

//  UPDATE
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") Integer bno, Model model) throws Exception {
		//Update페이지를 불러온다.
		logger.info("modifyGET......");

		BoardVO vo = service.read(bno);

		model.addAttribute("boardVO", vo);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
		//실제로 변경한 값을 넣어 수정 실행.
		logger.info("modifyPOST......");

		service.update(vo);
		rttr.addFlashAttribute("result", "Modify Success!!!");

		return "redirect:/board/read?bno=" + vo.getBno();
	}

//  DELETE
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removeGET(@RequestParam("bno") Integer bno, RedirectAttributes rttr) throws Exception {

		logger.info("removePOST......");

		service.remove(bno);
		rttr.addFlashAttribute("result", "Remove Success!!!");

		return "redirect:/board/listPage";
	}
}
