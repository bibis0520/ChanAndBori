package com.chan.search;

import com.chan.domain.BoardVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BoardSO extends BoardVO{

	// for 검색처리
	private String searchType;
	private String keyword;

}
