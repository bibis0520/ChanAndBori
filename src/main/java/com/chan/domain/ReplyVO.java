package com.chan.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {

	private Integer rno;		// <- PK
	private String  boardId;	// <- FK ( TBL_BOARD의 PK인 BOARD_ID를 참조하고 있다. )
	private String  replyText;
	private String  replyer;

	private Date   	regiDate;
	private Date    modiDate;

}
