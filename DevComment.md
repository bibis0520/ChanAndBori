TO-DO List
-----------
>- CRUD (Done)
>- Pagination (Done)
>- Reply
>- Login
>- File Upload

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

>##### 2019.01.04
>>**bno :** bno컬럼의 역할에대한 명확한 설계사항이 필요함

>>**프로젝트는 test포함 에러가 없는상태로 유지 : ** 새로운 사람이 checkout 받아도 이상없이 서버를 구동하며 Project Explorer에서 에러표시가 안뜨도록 상태를 유지하는 습관이 필요함

>##### 2019.01.06
>>**- 공통스크립트 :** commonJs.함수명( arguments ); validation, 공통 상수 등 공통적으로 필요한 항목을 위한 스크립트 추가

>>**- 네이버 스마트 에디터 **
>>> git 	  : <https://github.com/naver/smarteditor2>

>>> guide : <http://naver.github.io/smarteditor2/user_guide/>

>##### 2019.01.14
>>**- paging 처리 :** paging처리 향후 확장성을 위해 공통으로 분리, footer2 페이징 처리방식 참조(자바로직 불필요)

comment by kc
-----------
>##### 2018.12.31

>>`1` **README.md** 내용 일부 수정

>>`2` **JSP파일 디자인 일부 변경** (버튼, 테이블 mouse hover적용)

>>`3` **BoardMapper.xml** (resultForBoardVo => resultForBoardVO)
>>
>>>1. create()수정 중 : bno를 추가하기 위해 SEQ_COM.NEXTVAL을 사용했는데 2씩 추가되서, SEQ_COM.CURRVAL을 사용했는데 sqldelveloper의 결과와 다른 결과가 발견됨, sqldeveloper에는 1씩 제대로 추가되는데 spring에 적용 후 서버를 띄우면 ORA-08002 오류가 발생한다. SEQ_COM.CURRVAL은 NEXTVAL이 선행되어야 한다고하는데 이부분 수정해야 됨.
>>>
>>> ※  현재 만들어진 시퀀스는 순번의 의미가 아닌 unique한 key의 생성을 위한 용도로 순차채번을 위한 용도로 사용을 원할시에는 따로 시퀀스를 생성하여 사용하는걸 위해 구현)

>>>> **원인**
>>>>> 1. 현재시퀀스가 3, BNO가 3인 상태
>>>>> 2. application을 통해 sqlMapper의 insert쿼리 수행 : com.chan.persistence.BoardDAO.create
>>>>> 3. BOARD_ID의  FUNC_GET_SEQ_30함수 호출 (에 FUNC_GET_SEQ_30 함수 안의 NAXTVAL로 1추가되어 현재 시퀀스값 4)
>>>>> 4. BNO에서 NEXTVAL료 1이 추가됨 현재값 5
>>>>> 5. 이전 데이터의 BNO값만 비교시 2씩증가되어보임

>>>>**solution**
>>>>>1. 현재 현상은 insert문의 FUNC\_GET\_SEQ\_30 함수에서 채번이 선행 되었기때문에 발생한 현상임
>>>>>2. SEQ_COMMON은 채번의 의미가 아니라 unique한 key값을 생성하기 위한 용도로 생성한 시퀀스임
>>>>>3. 순서를 구분하기 위한 시퀀스가 필요시 SEQ_BNO와 같이 새로 시퀀스를 생성하는것이 용도에 적합함
>>>>>4. 만약 화면에 표시 또는 실제 순번에 의미가 있는 채번의 경우 시퀀스사용은 용도에 맞지않음
>>>>>5. 이유는 시퀀스의 CYCLE 옵션과 MAXVALUE때문임 때문에 게시판의 순번 컬럼은 어플리케이션에서 처리하는것이 바람직한것으로 생각됨

<hr>

>##### 2019.01.01

>>`1` **boardMapper.xml** (create부분의 bno추가하는 부분 수정)
>>
>>>1. sequence를 사용하지 않고 (SELECT NVL(MAX(BNO)+1, 1) FROM TBL_BOARD)를 사용하여 sequence와 상관없이 게시물이 있는 경우 1씩 추가되고 초기에 아무런 게시물도 없는 경우에는 1을 할당하고, 그 이후부터는 가장 큰 bno값의 +1된값을 할당한다.
>>	>  - bno가 3번인 경우 : Board_ID = 2019010121390800003YLXSTNGUYIA, BNO = 3
>>	>  - bno가 4번인 경우 : Board_ID = 2019010121405200004SYDOGTHXFMD, BNO = 4

>>>- 참고 : [Oracle의 NVL함수 & DECODE함수](http://applejara.tistory.com/303)

<hr>

>##### 2019.01.02

>>`1` **register.jsp 수정** (script 추가)
>>>- 참고 : [JQuery로 선택한 태그 요소값과 속성을 바꿔보자](http://rongscodinghistory.tistory.com/34)
>>>- 참고 : [JavaScript onclick Event](https://zetawiki.com/wiki/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8_onclick_%EC%9D%B4%EB%B2%A4%ED%8A%B8)
>>>- 참고 : [Spring으로 게시판 만들기](https://doublesprogramming.tistory.com/95)

>>`2` **BoardController.java의 조회(Read) 수정**
>>
>>>1. /board/viewRead -> /board/read 수정 (uri에 /board/read?bno=36과 같이 읽어올 수 있도록 수정)
>>>2. boardMapper.xml의 read부분, WHERE BOARD_ID = #{boardId} => WHERE BNO = #{bno} ( 1)에서 가져온 bno로 게시물을 조회할 수 있도록 수정)
>>>3. Service~DAO까지 read메서드의 매개변수 변경 : BoardVO searchVo => Integer bno

>>`3` **URI에 BNO값 전달하기** http://localhost:8080/board/read => http://localhost:8080/board/read?bno=36
>>
>>>1. listPage.jsp에 input을 없애고 해당 행을 클릭할 경우, table에서 bno를 가져와 uri를 만든다.
>>>2. 그리고 location.href = uri;를 통해 이동.
>>>3. 사실 리스트페이지에서는 bno를 가지고 하는일이 아직 명확하지 않아서 uri만 만들어 이동하도록 했는데 나중에 이부분은 수정이 필요할 것 같다.(아직 링크에 값을 전달하고 하는부분이 잘 이해되지 않은듯...)
>>>4. read.jsp에서는 BoardVO에서 받은 bno를 input[type=hidden]으로 저장해 놓도록 수정했다.(값이 저장되어 있는지 확인함)
>>>
>>>- 참고 : [W3School Location href Property](https://www.w3schools.com/jsref/prop_loc_href.asp)

>>`4` **UPDATE 구현 중**
>>
>>>1. BoardController에 UPDATE메서드 추가.(read와 마찬가지로 bno값을 가지고 'board/modify?bno=39'와 수정 실행
>>>2. boardMapper.xml에 UPDATE문 수정. 수정하면 현재 시간을 MODI_DATE컬럼에 넣음(! MODI_USER_ID가 필요한지 여부, 등록한 사람만 수정을 할수 있어야 할거같아서...)
>>>3. 수정할때는 제목과 내용만 수정할 수 있는데 제목이나 내용을 아얘 지우고 확인을 눌르면 수정이 되서, 이 부분 조건문으로 추가해야됨(빈곳으로 focus()되도록 수정)

>>`5` **CSS부분 수정해야될 부분**
>>
>>>1. READ, UPDATE부분에 BNO를 보여주기위한 부분을 추가했더니, 아래의 Footer와 겹치는 현상이 발생. 이부분 처리해야됨.(19.1.3해결)

<hr>

>##### 2019.01.03
>>`1` **CSS부분 수정**
>>
>>>1. header.jsp와 footer.jsp에 맨 하단과 맨 상단에 들어가있던 <div class="contents overflow-h padding-b40">를 각 jsp에 따로따로 다 추가했다. 페이지별로 디자인이 다를수도 있어서 일단 그렇게 처리하고, 추후에 공통적인 부분은 header.jsp와 footer.jsp에 다시 넣을 예정.
>>>2. 어제 버튼이 Footer영역과 겹치는 현상이 발견되어 그 부분은 Content div에 padding-bottom:40px 을 적용시켜 Contents div에 클래스로 추가했다.

>>`2` **UPDATE 구현**
>>
>>>1. 어제 제목이나 내용란을 빈칸으로 두고 확인을 눌러도 수정이 되는 현상이 발생해, 그부분을 script을 이용해 수정하였다. 처음에 전역 변수로 title, content를 잡아놨더니 이미 값이 저장되어 있었기 때문에 수정후 버튼을 클릭했을때 값이 있던 없던 alert가 발생되지 않았다. 그래서 title과 content의 값을 담는 변수를 function("#btnEnter").on("click", function(){ ... }); 안에 두어 값을 그때 그때 확인하여 빈칸일 경우 다시 그부분으로 focus()하도록 구현하였다.
>>>2. 수정이 완료된 후 BoardController의 update메서드에서 받는 redirect의 addFlashAttribute를 이용하여, 수정이 완료됬을 때 리다이렉트 되는 read페이지에서 그 값을 이용해 alert창이 맨위에 나타나도록 구현
>>>- 참고 : [[Spring] 17.SpringProject-수정,삭제구현](https://kookyungmin.github.io/server/2018/08/22/spring_17/)

>>`3` **DELETE 구현**
>>
>>>1. BoardController.java에 remove메서드 추가(GET방식, "board/remove?bno=39"와 같은 방식으로 게시물 삭제)
>>>2. 게시물을 등록하거나 삭제할 때, rttr의 addFlashAttribute를 이용하여 listPage가 redirect시에 받는 result메세지를 이용해 맨위에 alert창이 나타나도록 구현

>>`4` **DELETE의 경우 url공유가 필요한 항목이 아니며 주소를 이용한 게시물 무단삭제를 미약하게나마 방지하기 위해 POST방식으로 처리될것을 추천 (향후 권한처리구현으로 좀더 defensive하게 처리)**

>>`5` remove메서드 POST방식으로 변경.

>>`6` **예외 페이지 작성** (Controller Package안에 CommonExceptionAdvice.java)
>>
>>>1. 예외가 발생하면 error_common.jsp를 불러옴.
>>>2. TO-DO 결과는 제대로 불러오는데 `<li>`요소가 반복될때 값이 하나 찍고 개행되어야 하는데 화면 너비가 남으면 아래의 행이 위에 따라 들어온다. 한줄 찍고 개행하고 한줄찍고 개행하도록 수정해야됨.

<hr>

>##### 2019.01.04

>>`1` **페이징 처리에 필요한 쿼리 boardMapper.xml에 추가**
>>
>>>1. BNO순으로 최신 게시물부터 10개의 글만 조회하는 Query
>>
>>>- 참고 : [Oracle 10개의 행만 Select](https://m.blog.naver.com/PostView.nhn?blogId=nomadgee&logNo=220854618303&proxyReferer=https%3A%2F%2Fwww.google.com%2F)
>>>- 참고 : [Oracle 원하는 갯수의 행만 Select](https://blog.naver.com/giragi/46357666)
>>>- 참고 : [spring paging 공통모듈](https://handcoding.tistory.com/15)

<hr>

>##### 2019.01.05

>>`1` **서버 주소 업데이트(WAS서버 재생성)**
>>>1. WAS(tomcat) 		: 35.243.121.113
>>>2. JENKINS(jenkins) 	: 35.200.93.8
>>>3. Oracle & SVN 		: 35.200.69.237

>>`2` **배포 테스트 진행**

<hr>

>##### 2019.01.06

>**NaverSmartEditor 적용 중**

>>`1` **register.jsp 작업**
>>
>>>1. 유효화 작업( Content(textarea)부분 유효화 작업 )
>>>		- 해결	: 초기상태(아무것도 입력안했을때), Enter만 여러번 친 공백상태
>>> 	- 미해결	: 스페이스바만 반복 입력
>>>2.  DB 등록할때 태그에 정보가 들어간채로 입력된다. 이부분은 네이버스마트에디터에대한 정보를 좀더 찾아보고 해결해야 할 부분.
>>>3.  정보(서체, 폰트크기, 정렬 정보 등등...)를 가지고 DB에 들어가는게 맞는거같긴한데 이부분에대한 처리도 필요할 것 같다.
>>>
>>>- 참고 : [[Javascript & jQuery] 공백 여부 & 특수 문자 여부 & 비밀번호 패턴 체크](http://holybell87.tistory.com/30#.XDECpM8zbyg)
>>>- 참고 : [[JSP] 네이버의 스마트에디터(SmartEditor) 적용하기](http://blog.naver.com/PostView.nhn?blogId=javaking75&logNo=220249101012&parentCategoryNo=71&categoryNo=&viewDate=&isShowPopularPosts=true&from=search)

>>`2` **read.jsp 작업**
>>
>>> 1. TO-DO textarea부분 readonly가 될 수 있도록 처리해야됨 (readonly가 안되면, keydown이 발생할시 바로 리턴하는등의 방법을 쓰면 될것같다는 추측...)

>>`3` **modify.jsp 작업**
>>
>>>1. 제목과 내용의 빈칸인 상태로 등록될 경우의 처리는 read.jsp와 동일하게 처리(이부분도 유효화 작업이 필요함)

>>`4` **TO-DO 공통 스크립트 부분 작성해야 함...**

>>`5` **Pagenation 작업**
>>
>>>- 참고 : [ORACLE - ABOUT ROWNUM & TIP(PAGING)](http://greatkim91.tistory.com/52)
>>>- 참고 : [[spring/게시판] #6 게시판 페이징 처리하기](http://gangnam-americano.tistory.com/m/18)
>>>- 참고 : [웹 개발 페이지 처리(Paging) 방법 - 성능을 고려해보자](https://jeong-pro.tistory.com/88)

<hr>

>##### 2019.01.07

>>`1` **Pagenation 작업 (Criteria, Uri작업)**
>>
>>>1. "/board/listPage?page=1&perPageNum=10"으로 조회하기
>>>
>>>		1. Criteria.java(com.chan.domain)추가
>>>		2. BoardServiceImpl.listPage(Criteria cri)에서 필요한 값 계산 (BoardDAOImpl이 없어서 ServiceImpl에서 처리)
>>>		3. BoardServiceTest 추가(JUnit4 Test)

>>`2` **Pagenation 작업 (PageMaker, 하단에 Paging버튼 작업)**
>>
>>>- 참고 : [[Spring] 22.SpringProject-페이징 처리(4)](https://kookyungmin.github.io/server/2018/08/25/spring_22/)
>>>- 참고 : [Bootstrap Input group](https://getbootstrap.com/docs/4.1/components/input-group/#custom-select)
>>>- 참고 : [Bootstrap Pagination](https://getbootstrap.com/docs/4.1/components/pagination/)
>>>- 참고 : [jQuery / Method / .prop() - 속성값을 가져오거나 추가하는 메서드](https://www.codingfactory.net/10341)

>>`3` **Pagenation 작업 ( 등록, 수정, 삭제 후 본래 보고있던 페이지로 돌아가도록 처리하는 작업)**
>>>1. Criteria cri객체를 @ModelAttribute로 컨트롤러의 각 메서드에 매개변수로 줘서 jsp에서 받아 cri.makeQuery() (UriComponentsBuilder사용)로
>>>2. 수정, 목록, 삭제 버튼에서 page, perPageNum을 유지하려고했는데 보니, /board/listPage?page=1&perPageNum=10으로만 되있다. 생성자에서 잘못된건지 이부분 수정해야됨...

<hr>

>##### 2019.01.08

>>`1` 게시물 삭제를 하려니까 post method는 지원하지 않는다는 error message가 떠서보니 register.jsp, read.jsp, modify.jsp에 들어있던 등록, 수정, 삭제, 목록 버튼등이 모두 form태그 안에 들어가있었다. 그래서 그부분을 모두 form태그 밖으로 뺐더니 문제 해결.

>>`2` uri에 cri(page, perPageNum)의 값을 넘겨줘야 작업 후 본래 보고있던 페이지로 돌아올 수 있는데 이 부분이 해결되지 않는다.
>>
>>> 원인! (listPage에서 해당 행을 아무데나 클릭하면 해당 게시물로 이동했었는데 이부분에 내가 cri(page, perPageNum)를 넘겨줘도 아래의 소스처럼 내가 정해논 uri가 만들어져 들어갔기 때문에 제대로된 값을 받아도 올바른 uri가 만들어져 read.jsp까지 전달되지 못했다. 따라서 read.jsp에서도 당연히 그 값을 받지 못함.
>>
>>> ```
>>> // 테이블의 해당 행을 클릭하면 해당 게시물의 조회(read)페이지로 이동
	$(".boardRow").on('click', function(){
		var bno = $(this).children(".bno").text(),
		    uri = "/board/read?bno=" + bno;
		location.href = uri;
	});
>>> ```
>>>
>>> 위의 소스를 아래와같이 수정하니 pageMaker의 makeQuery가 제대로 동작했다.
>>>
>>> ```
>>> 	$(".boardRow").on('click', function(){
		var bno = $(this).children(".bno").text();
		//pageMaker.cri에 들어가있는 page에 대입되어있는 값을 가지고 makeQuery
		window.location.href = "/board/read${pageMaker.makeQuery(pageMaker.cri.page)}&bno=" + bno;
	});
>>> ```

>>`3` BoardController.register의 POST방식에서 새글등록후에는 자연스레 1페이지로 이동하도록 수정(perPageNum은 cri를 매개변수로 받는 메서드에서 getPerPageNum()을 통해 이미 저장되어있는 값을 그대로 사용)

>>`4` naverSmartEditor.js 추가(/src/main/webapp/resources/naverSmartEditor.js)
>>
>>> 1. var oEditors = []; 전역변수 (전역에서 값을 저장해놔야 submit()할때 값을 이용할 수 있다.)
>>> 2. register.jsp's naverSmartEditor()와 modify.jsp's naverSmartEditor(content)로 분리

>>`5` header.jsp에 있던 signup아이콘 삭제(login.jsp의 버튼으로 추가)

>>`6` DevComment.md파일 정리

<hr>

>##### 2019.01.12

>>`1` **검색조건 기능 추가 중...**
>>> 1.검색조건을 구현하기 위해 페이지 정보(page, perPageNum)를 가지고 있는 Criteria에 keyword(검색 키워드)와 searchType(검색 조건)을 추가

>>> ```
>>> //for 검색처리
private String searchType;
private String keyword;
>>> ```

>>> 2.listPage.jsp에 검색조건(searchType) 선택하는 부분과 검색어(keyword) 입력란 만들기
>>>
>>> - 검색조건 선택란 + 검색어 입력란 + 검색 버튼

>##### 2019.01.13

>>`1` **상단 메뉴바에서 게시판 버튼 누르면!!!** "board/listPage?page=1&perPageNum=10&searchType=&keyword="이 나오도록... 수정해야됨
>>> 이부분은 /board/listPage?page=1&perPageNum=10 로 하기로했다. 사실 게시판 버튼을 누른다는건 검색조건이나 이전에 유지하던 값들을 초기화 시킨다는 의미가 크기 때문에...
>>
>>`2` **검색조건 추가중...**
>>> 1. src/test/java내의 URIComponentBuildTest에 searchType, keyword를 추가한 테스트 하나 추가
>>> 2. listPage에서 검색(search)버튼을 누를경우 발생하는 이벤트 추가.
>>>
>>>> - PageMaker의 makeQuery메서드를 수정, 매개변수에 int page외에 boolean형의 needSearch를 받도록 했다.
>>>> - 이유는 검색조건이 처리되지 않은 경우, page번호의 li요소에는 링크값으로 searchType과 keyword를 유지하지 못했다. 따라서  UriComponentsBuilder로 만든 uri에 searchType과 keyword를 추가해 주기위해, pageMaker의 makeQuery메서드에 int page만 넘겨질 경우, makeQuery(page, true)가 return되어 자동으로 uri마지막에 searchType과 keyword가 추가된다.
>>>> - listPage에 searchBtn을 클릭할 경우에는 makeQuery(1, false)를 호출해 uri를 만들었는데, 클라이언트가 브라우저에서 검색조건과 검색어를 입력하고 검색 버튼을 누를 경우에 그 값을 가져와 uri를 만들어야 하기 때문이다.
>>>> - 그 부분에 true를 준다면,
>>>> `/board/listPage?page=1&perPageNum=10&searchType&keyword&searchType=t&keyword=chan`과 같이 searchType과 keyword가 두번 만들어진 것을 볼 수 있다. 따라서 false를 주어야 한다.

>>`3` **버튼을 눌렀을 경우, 예를들어 검색조건은 "제목"에서 "찬"이라는 내용을 가지고 검색을 한다면!**
>>> 내가 원하는 검색결과는 page번호의 li에 링크가 다음과 같이 붙어야한다.
>>> ```
>>> listPage?page=1&perPageNum=10&searchType=t&keyword=찬
>>> ```
>>>
>>> 하지만 결과는...
>>>
>>> ```
>>> listPage?page=1&perPageNum=10&searchType=c&keyword=%EC%B0%AC
>>> ```
>>>
>>> "찬"이란 string값이 인코딩되지 않은채로 li의 href에 출력된다. 찾아본 결과 javascript에서 제공하는 메서드에 encodeURIComponent라는게 있어서 encodeURIComponent(searchKeywordVal)이렇게 출력해 주었는데도 저런 결과가 나온다. 이부분은 해결해야됨. 일단 페이지 이동시에 검색조건과 키워드는 유지된다.(일단 진행하기로...)

<hr>

>##### 2019.01.18

>>`1` **paging처리중...**
>>> 1. Criteria와 PageMaker => CriteriaBackUp, PageMakerBackUp으로 바꿈
>>> 2. pageFooter, pageHeader파일 없애고 일단 listPage.jsp에 다 합쳐놈 (추후에 분리할 예정...)

<hr>

>##### 2019.01.22

>> `1` chanSpringContext.xml에 dataSource부분 수정
>> > 1. /src/main/resources/jdbc.properties란 파일에 설정값들을 두어 chanSpringContext.xml에서는 값을 가져와 사용하도록 처리
>> > 2. 보안에 더 좋은 방법인듯...

>> `2` Criteria와 PageMaker를 이용해 페이지값을 유지하는 방식으로 복원 ( .jsp에서 <% %>를 사용해서 하는 방법은 추후에 더할 예정 )

<hr>

>##### 2019.01.24

>>`1` Criteria.java에서 필요 없는 부분 삭제 ( boardMapper에서 pageNum, perPageNum을 가지고 계산하기 때문에 )

>> > ```
>> > 	//BoardServiceImpl.listPage(Criteria cri)에서 연산을 통해 입력받는 값, mapper에서도 사용되는 값.
		private int startBoardRowNum;
		private int endBoardRowNum;

>> > ```

>>`2` 검색조건 마저 작업중...

>>> 1. listPage.jsp하단에 검색( $("#searchBtn") )버튼 눌렀을 때 URI가 searchType과 keyword를 가질 수 있도록.

>>>> - criteria에 위의 네가지 값이 다 있기 때문에 검색조건(searchType, keyword)가 추가된 이후에 페이지를 이동하더라도 URI에 값이 유지되는 것 확인.
>>>> - 동적 쿼리 만들 예정...

<hr>

>##### 2019.01.25

>>`1` Criteria.java의 makeQuery() 수정.
>>
>>> 1. searchType의 값이 존재하면, UriComponentsBuilder에 의해 uri끝에 "&searchType=&keyword="를 추가하도록 했다.

>>`2` boardMapper의 listPage에 동적 SQL추가 ( MyBatis Dynamic SQL )
>>
>>> 1.searchType과 keyword는 Criteria 객체에 의해 URI에 전달되어 유지된다.
>>> 2.동적 SQL이 이용되어야 하는 부분
>>>
>>>> - 검색처리가 적용된 게시물 list
>>>> - 검색처리가 적용된 총 게시물의 수 <br>
>>>>   (위의 두 값을 알아야 검색처리가 완료된 listPage를 그릴 수 있기 때문.)

>>> 3.searchingMapper.xml 추가 ( pagingMapper를 include하는 방식과 동일하게 적용하려고 함 )
>>>
>>>> - concat의 parameter로 2개를 받아야 하는 것 같아서, concat내에 concat을 한번 더 써서 아래와 같이 작성했다. <br>
>>>> 참고 : [CONCAT(Oracle 호환성 함수)
](https://docs.aws.amazon.com/ko_kr/redshift/latest/dg/r_CONCAT.html)
>>>>
>>>> ```
<if test="searchType == 't'.toString()">
	AND TITLE LIKE CONCAT ( '%',  CONCAT ( #{keyword}, '%' ) )
</if>
...
...
>>>> ```

<hr>

>##### 2019.01.26

>>`1` File Upload 진행
>>
>>> 1. 주된 파일 형식은 이미지 파일(JPG, GIF, PNG)
>>> 2. 일반 파일인경우는 파일이 자동으로 다운로드 되도록 하고 이미지파일인 경우 확대해서 보여주도록 처리할 예정.
>>> 3. 파일 업로드하는 곳은 게시물을 등록하거나, 이미 등록된 글을 수정할 때 할 수 있도록 구현할 예정

>>`2` 파일 업로드를 위한 설정
>>
>>> 1.pom.xml
>>>
>>>> - imgscalr-lib
>>>> - commons-fileupload
>>>
>>> 2.servlet-context.xml(chanServletContext.xml)
>>>
>>>> - `<bean>`설정, multipartResolver
>>>> - 서버의 파일 저장경로 설정, 일단 프로젝트와 상관없는 경로에 fileUpload란 파일을 하나 만들어서 테스트를 했다. <br>
>>>>   (파일 경로는 추후에 수정할 필요가 있음)

>>`3` UploadController를 만들어 기본적으로 테스트 진행 ("/uploadForm")
>>
>>> 1.servlet-context.xml에 설정해둔 파일 경로를 이용하기 위해서 `@Resource`로 경로를 inject 했다.
>>>
>>> ```
>>> @Resource ( name = "uploadPath" )
>>> private String uploadPath;
>>>
>>> ```
>>>
>>> 2.get방식으로 jsp를 불러와서 post방식으로 파일을 업로드 한다. <br>
>>>    post방식에서는 파라미터를 업로드할 파일을 받은뒤 logger로 파일의 정보를 출력하도록 하고, 아래 부분에서 uploadFile이란 함수에 의해 파일이 최종적으로 아까 servlet-context.xml에 설정해둔 uploadPath로 업로드 되는 걸 확인.
>>>
>>>> - 로컬에서 업로드 되는 것은 확인했지만, was에 업로드한 뒤 실행해보니 "Exception ---> /Users/chan/Desktop/Workspace/fileUpload/bebd2281-0ca1-4b20-9661-43ab98945cb4_보리와.jpg (No such file or directory)
"와 같은 에러가 발생했다 아무래도 서버에선 인식하는 파일 경로가 다른 것 같다. 이부분 수정해야할 듯.

<hr>

>##### 2019.01.27

>>`1` Ajax를 이용한 파일 업로드 공부중
>>
>>> - 참고 : [javascript에서 이벤트전파를 중단하는 4가지 방법](https://programmingsummaries.tistory.com/313)
>>> - 참고 : [Drag & Drop](https://m.mkexdev.net/58)
>>> - drag & drop을 할 때 function에 event.preventDefault()를 하는 이유!<br>
>>>   HTML요소들은 기본 값으로 drop을 받아들이고 있지 않기 때문이다. 따라서 drop이 가능하도록 하려면 이벤트의 preventDefault()로 기본값을 취소해놓아야 한다.
>>>
>>>```
>>> <script>
>>>		$("#fileDrop").on("dragenter dragover", function(event){
>>>			event.preventDefault();
>>>		});
>>>
>>>		$("#fileDrop").on("drop", function(){
>>>			event.preventDefault();
>>>		});
>>>	</script>
>>>```
>>>
>>> - dragenter event : 이동할 데이터를 마우스로 끌어서 드롭 대상에 들어오는 순간 발생하는 이벤트
>>> - dragover event : 이동할 데이터를 마우스로 끌어서 드롭 대상에 올려 놓는 동안 계속해서 발생하는 이벤트
>>>
>> `2` 파일 업로드 관련되 사용되는 static 함수를 가지고 있는 클래스 생성 (com.chan.util/uploadFileUtils)

<hr>

>##### 2019.02.05

>>`1` File UPload구현중
>>
>>> Ajax방식으로 파일 업로드 구현
>>> 책보면서 실습 형식으로 테스트 해보았는데 막상 이전에 만든 register페이지에 붙이려니까 제대로 안된다.
>>> 이부분에 대한 이해가 좀더 필요할듯...

<hr>

>##### 2019.02.12

>>`1` register.jsp에서 첨부파일 붙이는 기능 구현중

>##### 2019.03.08

>>`1` 구글 클라우드 플랫폼에서 서버에 문제가 생겨 사용하지 못하게 되서 로컬에서만 프로젝트를 진행하기로 했다. 일단 책 순서대로 댓글처리, 파일 업로드 처리, 로그인 처리까지 하면 공부한 책에 있는 내용을 어느정도 커버할 수 있을 것 같다. 
>>
>>`2` 