package com.chan.controller;

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
import com.chan.search.BoardSO;
import com.chan.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService service;

//  CREATE
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register(BoardSO boardSO, Model model) throws Exception {

		logger.info("/board/register, GET");

		model.addAttribute("boardSO", boardSO);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(BoardVO boardVO,
					       RedirectAttributes rttr) throws Exception {

		logger.info("/board/register, POST, boardVO : {}", boardVO);

	    service.create(boardVO);

	    rttr.addFlashAttribute("result", "Register Success!!!");

	    rttr.addAttribute("pageNum", 1);
	    rttr.addAttribute("perPageNum", boardVO.getPerPageNum());

	    return "redirect:/board/listPage";
	}

//  READ
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("boardId") String boardId,
					 Model model) throws Exception {

		logger.info("/board/read, GET, boardId : {}", boardId);

		model.addAttribute("boardVO", service.read(boardId));
	}

//  UPDATE
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(@RequestParam("boardId") String boardId,
			           Model model) throws Exception {

		logger.info("/board/modify, GET, boardId : {}", boardId);

		model.addAttribute("boardVO", service.read(boardId));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO boardVO,
						 RedirectAttributes rttr) throws Exception {

		logger.info("/board/modify, POST, boardVO : {}", boardVO);

		service.update(boardVO);

		rttr.addFlashAttribute("result", "Modify Success!!!");

		rttr.addAttribute("boardId", boardVO.getBoardId());

//		rttr.addAttribute("page", cri.getPage());
//		rttr.addAttribute("perPageNum", cri.getPerPageNum());

		return "redirect:/board/read";
	}

//  DELETE
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(@RequestParam("boardId") String boardId,
			             RedirectAttributes rttr) throws Exception {

		logger.info("/board/remove, GET, boardId : {}", boardId);

		service.remove(boardId);
		rttr.addFlashAttribute("result", "Remove Success!!!");

//		rttr.addAttribute("page", cri.getPage());
//		rttr.addAttribute("perPageNum", cri.getPerPageNum());

		return "redirect:/board/listAll";
	}


//  LIST-ALL
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {

		logger.info("/board/listAll, GET");

		model.addAttribute("list", service.listAll());
	}

//  LIST by Pagination
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(BoardSO boardSO,
						 Model model) throws Exception {

		logger.info("/board/listPage, GET, pageNum : {}, perPageNum : {}", boardSO.getPageNum(), boardSO.getPerPageNum());

		model.addAttribute("list", service.listPage(boardSO));

	}

}
