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
	public void register() throws Exception {

		logger.info("register GET......");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(BoardVO boardVO, RedirectAttributes rttr) throws Exception {

		logger.info("register POST......");

	    service.create(boardVO);

	    rttr.addFlashAttribute("result", "Register Success!!!");

//	    rttr.addAttribute("page", 1);
//	    rttr.addAttribute("perPageNum", boardVO.getPerPageNum());

	    return "redirect:/board/listAll";
	}

//  READ
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("boardId") String boardId,
					 Model model) throws Exception {

		logger.info("readGET......");

		model.addAttribute("boardVO", service.read(boardId));
	}

//  UPDATE
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(BoardVO boardVO, Model model) throws Exception {

		logger.info("modifyGET......");

		model.addAttribute("boardVO", service.read(boardVO.getBoardId()));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO boardVO, RedirectAttributes rttr) throws Exception {

		logger.info("modifyPOST......");

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

		logger.info("remove......");

		service.remove(boardId);
		rttr.addFlashAttribute("result", "Remove Success!!!");

//		rttr.addAttribute("page", cri.getPage());
//		rttr.addAttribute("perPageNum", cri.getPerPageNum());

		return "redirect:/board/listAll";
	}


//  LIST-ALL
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {

		logger.info("listAllGET......Show Board's List");

		model.addAttribute("list", service.listAll());
	}

//  LIST by Pagination
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("boardSO") BoardSO boardSO,
						 Model model) throws Exception {

		model.addAttribute("list", service.listPage(boardSO));

	}

}
