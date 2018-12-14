package com.chan.controller;

import static com.chan.constants.Common.JSON_DATA_ROOT;
import static com.chan.constants.Common.JSON_TOTAL_COUNT;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chan.common.ResultMap;
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
	@ResponseBody public Map<String, Object> getBoardList(@RequestBody TestSO searchVo){
		//TO-DO @RequestBody @Valid TestSO searchVo 에서 에러가나서 @Valid 지워둠, 아래 마찬가지.
		Map<String, Object> resultMap = new ResultMap();

		resultMap.put(JSON_DATA_ROOT, testService.getBoardList(searchVo));
		resultMap.put(JSON_TOTAL_COUNT, testService.getBoardListTotalCount(searchVo));

		return resultMap;
	}

	@RequestMapping(value = "saveBoard", method = RequestMethod.POST)
	@ResponseBody public Map<String, Object> saveBoard(@RequestBody TestVO saveVo){
	  testService.saveBoard(saveVo);

	  return new ResultMap();
	}
}
