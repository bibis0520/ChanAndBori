package com.chan.controller;

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

//  CREATE
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register(BoardVO boardVO,
						 @ModelAttribute("cri") Criteria cri,
					     Model model) throws Exception {

		logger.info("/board/register, GET");

		model.addAttribute("cri", cri);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(BoardVO boardVO,
			               Criteria cri,
			               RedirectAttributes rttr) throws Exception {

		logger.info("/board/register, POST");

	    service.create(boardVO);

	    rttr.addFlashAttribute("result", "Register Success!!!");
	    rttr.addAttribute("pageNum", 1);
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());

	    return "redirect:/board/listPage";
	}

//  READ
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("boardId") String boardId,
				 	 @ModelAttribute("cri") Criteria cri,
					 Model model) throws Exception {

		logger.info("/board/read?boardId={}, GET", boardId);

		model.addAttribute(service.read(boardId));
	}

//  UPDATE
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(@RequestParam("boardId") String boardId,
			           @ModelAttribute("cri") Criteria cri,
			           Model model) throws Exception {

		logger.info("/board/modify?boardId={}, GET", boardId);

		BoardVO boardVO = service.read(boardId);

		model.addAttribute("boardVO", boardVO);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO boardVO,
						 Criteria cri,
						 RedirectAttributes rttr) throws Exception {

		logger.info("/board/modify?boardId={}, POST", boardVO.getBoardId());

		service.update(boardVO);

		rttr.addFlashAttribute("result", "Modify Success!!!");
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("boardId", boardVO.getBoardId());

		return "redirect:/board/read";
	}

//  DELETE
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(@RequestParam("boardId") String boardId,
			             Criteria cri,
			             RedirectAttributes rttr) throws Exception {

		logger.info("/board/remove?boardId={}, POST", boardId);

		service.remove(boardId);

		rttr.addFlashAttribute("result", "Remove Success!!!");
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/board/listPage";
	}

//  LIST-ALL
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {

		logger.info("/board/listAll, GET");

		model.addAttribute("list", service.listAll());
	}

//  LIST by Pagination
	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void listPage(Criteria cri,
						 Model model) throws Exception {

		logger.info("/board/listPage?pageNum={}&perPageNum={}, GET", cri.getPageNum(), cri.getPerPageNum());

		logger.info("cri에 들어있는 값 : {}", cri.toString());

		model.addAttribute("list", service.listPage(cri));

		PageMaker pageMaker = new PageMaker(cri);

		pageMaker.setTotalDataCnt(service.getTotalDataCnt(cri));

		model.addAttribute("pageMaker", pageMaker);
	}

}