package com.chan.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVO {

	private String regiUserId;
	private Date regiDate;
	private String modiUserId;
	private Date modiDate;
}
