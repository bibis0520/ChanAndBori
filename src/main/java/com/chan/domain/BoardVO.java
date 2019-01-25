package com.chan.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {

	private String  boardId;
	private String  hrankBoardId;
	private Integer bno;
	private String  title;
	private String  content;
	private Integer viewCnt;
	private String 	regiUserId;
	private Date	regiDate;
	private String 	modiUserId;
	private Date 	modiDate;

}
