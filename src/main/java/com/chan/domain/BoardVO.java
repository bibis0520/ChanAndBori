package com.chan.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {

	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
	
//	create table tbl_board(
//			bno int not null auto_increment,
//		    title varchar(200) not null,
//		    content text null,
//		    writer varchar(50) not null,
//		    regdate timestamp not null default now(),
//		    viewcnt int default 0,
//		    primary key(bno)
//	);
	
}
