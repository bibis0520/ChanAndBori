package com.chan.domain;

import lombok.Data;

@Data
public class BoardVO extends BaseVO{

	private String boardId;
	private String hrankBoardId;
	private Integer bno;
	private Integer seq;
	private String title;
	private String content;
	private Integer viewCnt;
	
	
//	CREATE TABLE TBL_BOARD(
//		    BOARD_ID VARCHAR2(30),
//		    HRANK_BOARD_ID VARCHAR2(30),
//		    BNO NUMBER(10),
//		    SEQ NUMBER(5),
//		    TITLE VARCHAR2(200),
//		    CONTENT CLOB,
//		    REGI_USER_ID VARCHAR2(50),
//		    REGI_DATE DATE,
//		    MODI_USER_ID VARCHAR2(50), 
//		    MODI_DATE DATE,    
//		    VIEW_CNT NUMBER(3),
//		    CONSTRAINT BOARD_PK PRIMARY KEY (BOARD_ID)
//		);
	
}
