package com.chan.domain;

import java.util.Date;

import com.chan.domain.BaseVO;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestVO extends BaseVO {
	
	private Integer bno;
	private String  title;
	private String  content;
	private String  writer;
	private Date    regdate;
	private int     viewcnt;

}
