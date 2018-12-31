package com.chan.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVO {
	// 등록사용자ID
	private String regiUserId;

	// 등록일시
	private Date regiDate;

	// 수정사용자ID
	private String modiUserId;

	// 서정일시
	private Date modiDate;
}
