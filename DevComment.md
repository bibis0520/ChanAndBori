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

>##### 2019.01.01
>>**디비변경**
- who컬럼 테이블의 최하단으로 통일(board테이블의 view_cnt컬럼 후컬럼 위로 변경)
- board테이블의 view_cnt값 및 who컬럼의 일시컬럼 default값 추가
- 테이블, 컬럼 comment 추가

comment by kc
-----------
>##### 2018.12.31
>>** README.md :** 내용 일부 수정

>>** JSP파일 디자인 일부 변경 :** 버튼, 테이블 mouse hover적용

>>** BoardMapper.xml:** resultForBoardVo => resultForBoardVO
>>>- create()수정 중 : bno를 추가하기 위해 SEQ_COM.NEXTVAL을 사용했는데 2씩 추가되서, SEQ_COM.CURRVAL을 사용했는데 sqldelveloper의 결과와 다른 결과가 발견됨, sqldeveloper에는 1씩 제대로 추가되는데 spring에 적용 후 서버를 띄우면 ORA-08002 오류가 발생한다. SEQ_COM.CURRVAL은 NEXTVAL이 선행되어야 한다고하는데 이부분 수정해야 됨.
>>>>※현재 만들어진 시퀀스는 순번의 의미가 아닌 unique한 key의 생성을 위한 용도로 순차채번을 위한 용도로 사용을 원할시에는 따로 시퀀스를 생성하여 사용하는걸 위해 구현)

>>>>>**원인**
1. 현재시퀀스가 3, BNO가 3인 상태
2. application을 통해 sqlMapper의 insert쿼리 수행 : com.chan.persistence.BoardDAO.create
3. BOARD_ID의  FUNC_GET_SEQ_30함수 호출 (에 FUNC_GET_SEQ_30 함수 안의 NAXTVAL로 1추가되어 현재 시퀀스값 4)
4. BNO에서 NEXTVAL료 1이 추가됨 현재값 5
5. 이전 데이터의 BNO값만 비교시 2씩증가되어보임

>>>>>**solution**
>>>>>>현재 현상은 insert문의 FUNC\_GET\_SEQ\_30 함수에서 채번이 선행 되었기때문에 발생한 현상임

>>>>>>SEQ_COMMON은 채번의 의미가 아니라 unique한 key값을 생성하기 위한 용도로 생성한 시퀀스임

>>>>>>순서를 구분하기 위한 시퀀스가 필요시 SEQ_BNO와 같이 새로 시퀀스를 생성하는것이 용도에 적합함

>>>>>>만약 화면에 표시 또는 실제 순번에 의미가 있는 채번의 경우 시퀀스사용은 용도에 맞지않음

>>>>>>이유는 시퀀스의 CYCLE 옵션과 MAXVALUE때문임 때문에 게시판의 순번 컬럼은 어플리케이션에서 처리하는것이 바람직한것으로 생각됨

>##### 2019.01.01
>>** boardMapper.xml :** create부분의 bno추가하는 부분 수정
>>>- sequence를 사용하지 않고 (SELECT NVL(MAX(BNO)+1, 1) FROM TBL_BOARD)를 사용하여 sequence와 상관없이 게시물이 있는 경우 1씩 추가되고 초기에 아무런 게시물도 없는 경우에는 1을 할당하고, 그 이후부터는 가장 큰 bno값의 +1된값을 할당한다.
>>>- bno가 3번인 경우 : Board_ID = 2019010121390800003YLXSTNGUYIA, BNO = 3
>>>- bno가 4번인 경우 : Board_ID = 2019010121405200004SYDOGTHXFMD, BNO = 4
>>>- 참고 : [Oracle의 NVL함수 & DECODE함수](http://applejara.tistory.com/303)

>##### 2019.01.02
>>** register.jsp 수정:** script 추가
>>>- 참고 : [JQuery로 선택한 태그 요소값과 속성을 바꿔보자](http://rongscodinghistory.tistory.com/34)
>>>- 참고 : [JavaScript onclick Event](https://zetawiki.com/wiki/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8_onclick_%EC%9D%B4%EB%B2%A4%ED%8A%B8)
>>>- 참고 : [Spring으로 게시판 만들기](https://doublesprogramming.tistory.com/95)

>>** BoardController.java의 조회(Read) 수정 : **
>>>- 1) /board/viewRead -> /board/read 수정 (uri에 /board/read?bno=36과 같이 읽어올 수 있도록 수정)
>>>- 2) boardMapper.xml의 read부분, WHERE BOARD_ID = #{boardId} => WHERE BNO = #{bno} ( 1)에서 가져온 bno로 게시물을 조회할 수 있도록 수정)
>>>- 3) Service~DAO까지 read메서드의 매개변수 변경 : BoardVO searchVo => Integer bno

>>** URI에 BNO값 전달하기 :** http://localhost:8080/board/read => http://localhost:8080/board/read?bno=36
>>>- 1) listPage.jsp에 input을 없애고 해당 행을 클릭할 경우, table에서 bno를 가져와 uri를 만든다.
>>>- 2) 그리고 location.href = uri;를 통해 이동.
>>>- 3) 사실 리스트페이지에서는 bno를 가지고 하는일이 아직 명확하지 않아서 uri만 만들어 이동하도록 했는데 나중에 이부분은 수정이 필요할 것 같다.(아직 링크에 값을 전달하고 하는부분이 잘 이해되지 않은듯...)
>>>- 4) read.jsp에서는 BoardVO에서 받은 bno를 input[type=hidden]으로 저장해 놓도록 수정했다.(값이 저장되어 있는지 확인함)
>>>- 참고 : [W3School Location href Property](https://www.w3schools.com/jsref/prop_loc_href.asp)

>>** UPDATE 구현 중 :**
>>>- 1) BoardController에 UPDATE메서드 추가.(read와 마찬가지로 bno값을 가지고 'board/modify?bno=39'와 수정 실행
>>>- 2) boardMapper.xml에 UPDATE문 수정. 수정하면 현재 시간을 MODI_DATE컬럼에 넣음(! MODI_USER_ID가 필요한지 여부, 등록한 사람만 수정을 할수 있어야 할거같아서...)
>>>- 3) 수정할때는 제목과 내용만 수정할 수 있는데 제목이나 내용을 아얘 지우고 확인을 눌르면 수정이 되서, 이 부분 조건문으로 추가해야됨(빈곳으로 focus()되도록 수정)

>>** CSS부분 수정해야될 부분 :**
>>>- READ, UPDATE부분에 BNO를 보여주기위한 부분을 추가했더니, 아래의 Footer와 겹치는 현상이 발생. 이부분 처리해야됨.




