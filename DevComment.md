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
>>>>※핸재 만들어진 시퀀스는 순번의 읨미가 아닌 unique한 key의 생성을 위한 용도로 순차채번을 위한 용도로 사용을 원할시에는 따록 시퀀스를 생섯하여 사용하는걸춴
위 현)

>>>>>**원인**
1. 현재시퀀스가 3, BNO가 3인 상태
2. application을 통해 sqlMapper의 insert쿼리 수행 : com.chan.persistence.BoardDAO.create
3. BOARD_ID의  FUNC_GET_SEQ_30함수 호출 (에 FUNC_GET_SEQ_30 함수 안의 NAXTVAL로 1추가되어 현재 시퀀스값 4)
4. BNO에서 NEXTVAL료 1이 추가됨 현재값 5
5. 이전 데이터의 BNO값만 비교시 2씩증가되어보임

>>>>>**solutio***
>>>>>>현재 현상은 insert문의 FUNC\_GET\_SEQ\_30 함수에서 채번이 선행 되었기때문에 발생한 현상임

>>>>>>SEQ_COMMON은 채번의 의미가 아니라 unique한 key값을 생성하기 위한 용도로 생성한 시퀀스임

>>>>>>순서를 구분하기 위한 시퀀스가 필요시 SEQ_BNO와 같이 새로 시퀀스를 생성하는것이 용도에 적합함

>>>>>>만약 화면에 표시 또는 실제 순번에 의미가 있는 채변의 경우 시퀀스사용은 용도에 맞지않음

>>>>>>이유는 시퀀스의 CYCLE 옵션과 MAXVALUE때문임 때문에 게시판의 순번컬럼은 어플리케이션에서 처리하는것이 바람직한것으로 생각됨