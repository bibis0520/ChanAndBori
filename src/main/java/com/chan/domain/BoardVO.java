package com.chan.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true, includeFieldNames=true)
public class BoardVO extends BaseVO {

	private String  boardId;
	private String  hrankBoardId;
	private Integer bno;
	private String  title;
	private String  content;
	private Integer viewCnt;

}
