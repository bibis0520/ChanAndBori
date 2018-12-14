package com.chan.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVO {
	
	private String regUserId;
	private String regUserNm;
	private Date   regDate;

	private String modiUserId;
	private String modiUserNm;
	private Date   modiDate;
}
