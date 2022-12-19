-- 테이블삭제
DROP TABLE BOOK;

-- 시퀀스삭제
DROP SEQUENCE SEQ_MNO;

CREATE TABLE BOOK(
    MNO NUMBER PRIMARY KEY,                     -- 회원번호
    MID VARCHAR2(20) NOT NULL UNIQUE,           -- 회원아이디
    MPWD VARCHAR2(20) NOT NULL,                 -- 회원비밀번호
    MNAME VARCHAR2(20) NOT NULL,                -- 회원명
    MPHONE VARCHAR2(11) NOT NULL,               -- 회원연락처
    BNO NUMBER NOT NULL UNIQUE,                 -- 대여도서번호
    BNAME VARCHAR2(50) NOT NULL,                -- 대여도서명
    WNAME VARCHAR2(30) NOT NULL,                -- 작가명
    PNAME VARCHAR2(30) NOT NULL,                -- 출판사명
    RENTDATE DATE DEFAULT SYSDATE NOT NULL,     -- 대여일
    ENDDATE DATE DEFAULT SYSDATE+7 NOT NULL     -- 반납일
);

CREATE SEQUENCE SEQ_MNO
NOCACHE;

INSERT INTO BOOK
VALUES(SEQ_MNO.NEXTVAL, 'user01', 'pass01', '김수진', '01050499858', 1, '트렌드 코리아 2023', '김난도 외', '미래의 창', DEFAULT, DEFAULT);

COMMIT;
