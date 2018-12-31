TO-DO List
-----------
>- CRUD

comment by hsm
-----------
>##### 2018.12.31
>>**listPage.jsp :** data-board-id =>  jquery : data()
>>>- 참고 : <http://www.nextree.co.kr/p10155/>

>>**listPage -> read :** ModelAndView
>>>- 참고 : <https://hongku.tistory.com/116>

>>**서버 timezone :** 서울로 변경 (dpkg-reconfigure tzdata); 변경완료서버 :oracle, jenkins, sonarqube; TO-DO: was
>>>- 참고 : <http://ngee.tistory.com/643>

comment by kc
-----------
>##### 2018.12.31
>>** README.md :** 내용 일부 수정

>>** JSP파일 디자인 일부 변경 :** 버튼, 테이블 mouse hover적용

>>** BoardMapper.xml:** resultForBoardVo => resultForBoardVO
>>>- create()수정 중 : bno를 추가하기 위해 SEQ_COM.NEXTVAL을 사용했는데 2씩 추가되서, SEQ_COM.CURRVAL을 사용했는데 sqldelveloper의 결과와 다른 결과가 발견됨, sqldeveloper에는 1씩 제대로 추가되는데 spring에 적용 후 서버를 띄우면 ORA-08002 오류가 발생한다. SEQ_COM.CURRVAL은 NEXTVAL이 선행되어야 한다고하는데 이부분 수정해야 됨.