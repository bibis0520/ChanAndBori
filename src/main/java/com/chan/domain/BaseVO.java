package com.chan.domain;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestVO{
  private String regUserId;
  private String regUserNm;
	private Date regDate;

	private String modiUserId;
	private String modiUserNm;
	private Date modiDate;
}
