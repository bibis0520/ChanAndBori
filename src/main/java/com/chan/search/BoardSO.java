package com.chan.search;

import com.chan.domain.BoardVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true, includeFieldNames=true)
public class BoardSO extends BoardVO{

	// for 검색처리
	private String searchType;
	private String keyword;

}
