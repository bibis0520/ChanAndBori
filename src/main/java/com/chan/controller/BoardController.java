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

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService service;

//  LIST
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {

		logger.info("listAllGET......Show Board's List");

		model.addAttribute("list", service.listAll());
	}

	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception {

		System.out.println("현재 " + cri.getPage() + "페이지를 조회하였고, 한 페이지당 " + cri.getPerPageNum() + "개의 게시물을 조회중...");

		List<BoardVO> boards = service.listPage(cri);

		model.addAttribute("list", boards);

		int totalBoardCount = service.getTotalDataCnt(cri);

		PageMaker pageMaker = new PageMaker(cri);

		pageMaker.setTotalDataCnt(totalBoardCount);

		model.addAttribute("pageMaker", pageMaker);
	}


//  CREATE
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register(BoardVO boardVO, Model model) throws Exception {

		logger.info("registerGET......");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(BoardVO vo, RedirectAttributes rttr) throws Exception {

		logger.info("registerPOST......");

	    service.create(vo);
	    rttr.addFlashAttribute("result", "Register Success!!!");

	    return "redirect:/board/listPage";
	}

//  READ
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") Integer bno,
				 	 @ModelAttribute("cri") Criteria cri,
					 Model model) throws Exception {
		//@RequestParam -> 사용자의 요청(request)에서 특정한 파라미터값을 찾아낼때 사용.
		logger.info("readGET......");

		//URI에서 얻어낸 bno값으로 게시물을 읽어온다.
		model.addAttribute(service.read(bno));
	}

//  UPDATE
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(@RequestParam("bno") Integer bno, Model model) throws Exception {
		//Update페이지를 불러온다.
		logger.info("modifyGET......");

		BoardVO vo = service.read(bno);

		model.addAttribute("boardVO", vo);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO vo, RedirectAttributes rttr) throws Exception {
		//실제로 변경한 값을 넣어 수정 실행.
		logger.info("modifyPOST......");

		service.update(vo);
		rttr.addFlashAttribute("result", "Modify Success!!!");

		return "redirect:/board/read?bno=" + vo.getBno();
	}

//  DELETE
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(@RequestParam("bno") Integer bno,
			             Criteria cri,
			             RedirectAttributes rttr) throws Exception {

		logger.info("remove......");

		service.remove(bno);
		rttr.addFlashAttribute("result", "Remove Success!!!");
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());

		return "redirect:/board/listPage";
	}
}
