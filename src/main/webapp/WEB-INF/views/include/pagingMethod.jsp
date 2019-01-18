<%@page import="org.springframework.web.util.UriComponentsBuilder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%! /* 페이징 처리를 위해 필요한 메서드 및 전역변수가 있는 곳(따로 전역변수는 선언 안함) */

	public String makeURI(int pageNum, int perPageNum){
		UriComponentsBuilder uriComponentsBuilder  = UriComponentsBuilder.newInstance()
														  				 .queryParam("pageNum", pageNum)
														  				 .queryParam("perPageNum", perPageNum);

		return uriComponentsBuilder.build().encode().toString();
	}

%>