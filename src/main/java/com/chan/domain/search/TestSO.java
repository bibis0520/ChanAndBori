package com.chan.domain.search;

import java.util.Date;
import com.chan.domain.TestVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestSO extends TestVO{
	private int pageNo;
	private int pageSize;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyyMMdd", timezone="Asia/Seoul")
	private Date regdateFrom;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyyMMdd", timezone="Asia/Seoul")
	private Date regdateTo;
}
