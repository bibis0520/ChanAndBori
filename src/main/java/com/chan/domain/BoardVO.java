package com.chan.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BoardVO extends BaseVO {

	private String  boardId;
	private String  hrankBoardId;
	private Integer bno;
	private String  title;
	private String  content;
	private Integer viewCnt;

}
