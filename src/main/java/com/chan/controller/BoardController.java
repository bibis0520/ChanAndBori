package com.chan.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chan.domain.BoardVO;
import com.chan.domain.Criteria;
import com.chan.domain.PageMaker;
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

		// cri를 가지로 현재 페이지에 해당하는 게시물들을 조회해온다.
		List<BoardVO> boards = service.listPage(cri);

		model.addAttribute("list", boards);

		// PageMaker객체 생성
		PageMaker pageMaker = new PageMaker(cri);

		// 전체 게시물의 수를 구함
		int totalBoardCount = service.getTotalDataCnt(cri);

		// pageMaker에게 전체 게시물의 수를 전달해주면, pageMaker.calcData()에 의해 페이징처리에 필요한
		// startPageNum, endPageNum, prev, next가 계산된다.
		pageMaker.setTotalDataCnt(totalBoardCount);

		model.addAttribute("pageMaker", pageMaker);
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
	public void readGET(@RequestParam("bno") Integer bno,
						@ModelAttribute("cri") Criteria cri,
						Model model) throws Exception {
		//@RequestParam -> 사용자의 요청(request)에서 특정한 파라미터값을 찾아낼때 사용.
		logger.info("readGET......");

		//URI에서 얻어낸 bno값으로 게시물을 읽어온다.
		model.addAttribute(service.read(bno));
	}

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
