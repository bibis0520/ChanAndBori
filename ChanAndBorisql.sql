-- TBL_BOARD 테이블 (게시물 관리 테이블) -----------------------------------------------------------------------------
CREATE TABLE TBL_BOARD(
    BOARD_ID VARCHAR2(30),
    HRANK_BOARD_ID VARCHAR2(30),
    BNO NUMBER(10),
    SEQ NUMBER(5),
    TITLE VARCHAR2(200),
    CONTENT CLOB,
    REGI_USER_ID VARCHAR2(50),
    REGI_DATE DATE,
    MODI_USER_ID VARCHAR2(50), 
    MODI_DATE DATE,    
    VIEW_CNT NUMBER(3),
    CONSTRAINT BOARD_PK PRIMARY KEY (BOARD_ID)
);

---- BoardCtn, 총 게시물의 수를 가져오는 Query
SELECT COUNT(1) FROM TBL_BOARD;

---- TBL_BOARD의 모든 행 삭제
DELETE FROM TBL_BOARD;

---- TBL_BOARD에서 BNO의 역순으로 게시물 불러오기
SELECT * FROM TBL_BOARD ORDER BY BNO DESC;

---- TBL_BOARD에 테스트 데이터 집어넣기 
INSERT INTO	/* com.chan.persistence.BoardDAO.create */
		TBL_BOARD
				( BOARD_ID,	BNO, TITLE,	CONTENT, REGI_USER_ID, REGI_DATE, VIEW_CNT )
		VALUES
				( FUNC_GET_SEQ_30(), (SELECT NVL(MAX(BNO)+1, 1) FROM TBL_BOARD), 'TEST TITLE', 'TEST CONTENT', 'TEST REGI_USER_ID', SYSDATE, 0 );
                
---- 이전에 추가된 데이터의 BNO를 가지고 새로 게시물이 등록될 때, BNO를 만들어주는 Query
SELECT NVL(MAX(BNO+1), 1) FROM TBL_BOARD;

---- 10개의 데이터만 가져오는 Query

------ 1)
SELECT 
    * 
FROM 
    (
        SELECT
                *
        FROM
                TBL_BOARD
        ORDER BY
                BNO DESC
    ) 
WHERE 
    ROWNUM <= 10;
    
------ 2)    
SELECT * FROM TBL_BOARD WHERE BNO BETWEEN 20 AND 30 ORDER BY BNO DESC;

------ 3)
SELECT *
FROM
    (
    SELECT ROWNUM RNUM, TBL_BOARD.*
    FROM
        (
        SELECT *
        FROM TBL_BOARD
        ORDER BY BNO DESC
        )
        TBL_BOARD
    WHERE ROWNUM <= 10
    )
WHERE RNUM > 0;

------ 4) 이걸 사용하기로 했다!
------ 마지막 행을 WHERE RNUM BETWEEN #{start} AND #{last}
SELECT              ROWNUM RNUM,
                    BOARD_ID,
					HRANK_BOARD_ID,
					BNO,
					SEQ,
					TITLE,
					CONTENT,
					REGI_USER_ID,
					REGI_DATE,
					MODI_USER_ID,
					MODI_DATE,
					VIEW_CNT
FROM 
    (
    SELECT ROWNUM RNUM, TBL_BOARD.BOARD_ID, TBL_BOARD.HRANK_BOARD_ID, TBL_BOARD.BNO, TBL_BOARD.SEQ, TBL_BOARD.TITLE, TBL_BOARD.CONTENT,
           TBL_BOARD.REGI_USER_ID, TBL_BOARD.REGI_DATE, TBL_BOARD.MODI_USER_ID, TBL_BOARD.MODI_DATE, TBL_BOARD.VIEW_CNT
    FROM
        (
        SELECT
                    BOARD_ID,
					HRANK_BOARD_ID,
					BNO,
					SEQ,
					TITLE,
					CONTENT,
					REGI_USER_ID,
					REGI_DATE,
					MODI_USER_ID,
					MODI_DATE,
					VIEW_CNT
        FROM TBL_BOARD
        ORDER BY BNO DESC
        )
        TBL_BOARD
    )
WHERE RNUM BETWEEN 1 AND 10;
------ WHERE RNUM BETWEEN #{page} AND #{perPageNum}  

------ 5) 
select B.rnum, B.BOARD_ID, B.HRANK_BOARD_ID, B.BNO, B.SEQ, B.TITLE, B.CONTENT, B.REGI_USER_ID, B.REGI_DATE, B.MODI_USER_ID, B.MODI_DATE, B.VIEW_CNT
from
    (select rownum as rnum, A.BOARD_ID, A.HRANK_BOARD_ID, A.BNO, A.SEQ, A.TITLE, A.CONTENT, A.REGI_USER_ID, A.REGI_DATE, A.MODI_USER_ID, A.MODI_DATE, A.VIEW_CNT
    from (
        select BOARD_ID, HRANK_BOARD_ID, BNO, SEQ, TITLE, CONTENT, REGI_USER_ID, REGI_DATE, MODI_USER_ID, MODI_DATE, VIEW_CNT
        from TBL_BOARD
        order by bno desc)A
    where rownum <= 10 )B
where B.rnum >= 1;

-- 게시물의 총 개수를 구하는 Query
SELECT COUNT(*) FROM TBL_BOARD;

----dummy data 삽입(procedure)
BEGIN
    FOR i in 1..500 LOOP
    INSERT INTO	
		TBL_BOARD
				( BOARD_ID, BNO, TITLE, CONTENT, REGI_USER_ID, REGI_DATE,	VIEW_CNT )
		VALUES
				( FUNC_GET_SEQ_30(), (SELECT NVL(MAX(BNO)+1, 1) FROM TBL_BOARD), 'TEST TITLE', 'TEST CONTENT', 'TEST REGI_USER_ID',	SYSDATE, 0 );
    END LOOP;
END;

-- TBL_USER 테이블 (회원 관리 테이블) --------------------------------------------------------------------------------
CREATE TABLE TBL_USER(
    USER_ID VARCHAR2(30),
    USER_PW VARCHAR2(256),
    USER_NAME VARCHAR2(50),
    USER_EMAIL VARCHAR2(100),
    REGI_USER_ID VARCHAR2(50),
    REGI_DATE DATE,
    MODI_USER_ID VARCHAR2(50), 
    MODI_DATE DATE,
    CONSTRAINT USER_PK PRIMARY KEY (USER_ID)
);

-- SEQUENCE 관련 QUERY -------------------------------------------------------------------------------------------
---- SEQ_COMMON ( 1~10000의 수를 만들어주는 SEQUENCE, TBL_BOARD의 BOARD_ID(Primary key)를 만드는데 사용 )
CREATE SEQUENCE 
    SEQ_COMMON 
START WITH 1 
INCREMENT BY 1 
MAXVALUE 10000 
MINVALUE 1 CYCLE;

DROP SEQUENCE SEQ_COMMON;
ALTER SEQUENCE SEQ_COMMON INCREMENT BY -2;
SELECT SEQ_COMMON.CURRVAL FROM DUAL;
ALTER SEQUENCE SEQ_COMMON INCREMENT BY 1;

-- FUNCTION 관련 QUERY -------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION FUNC_GET_SEQ_30
RETURN VARCHAR2
IS
BEGIN
    RETURN  TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS')||LPAD(SEQ_COMMON.NEXTVAL, 5, '0')||DBMS_RANDOM.STRING('U', 11);
END;

SELECT FUNC_GET_SEQ_30() FROM DUAL;