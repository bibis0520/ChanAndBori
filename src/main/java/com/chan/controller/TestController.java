package com.chan.controller;

import static com.chan.constants.Common.JSON_DATA_ROOT;
import static com.chan.constants.Common.JSON_TOTAL_COUNT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chan.controller.ResultMap;
import com.chan.domain.TestVO;
import com.chan.domain.search.TestSO;
import com.chan.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private TestService testService;

	@RequestMapping(value = "getBoardList", method = RequestMethod.POST)
	@ResponseBody public Map<String, Object> getBoardList(@RequestBody @Valid TestSO searchVo){
		Map<String, Object> resultMap = new ResultMap();

		resultMap.put(JSON_DATA_ROOT, testService.getBoardList(searchVo));
		resultMap.put(JSON_TOTAL_COUNT, testService.getBoardListTotalCount(searchVo));

		return resultMap;
	}

	@RequestMapping(value = "saveBoard", method = RequestMethod.POST)
	@ResponseBody public Map<String, Object> saveBoard(@RequestBody @Valid TestVO saveVo){
	  testService.saveBoard(saveVo);

	  return new ResultMap();
	}
}
