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
VALUES(SEQ_MNO.NEXTVAL, 'admin', 'admin1', '김수진', '01050499858', 0, '프로그램 사용설명서', '설계자', '설계자 뇌', DEFAULT, DEFAULT);

INSERT INTO BOOK
VALUES(SEQ_MNO.NEXTVAL, 'user01', 'pass01', '김수진', '01050499858', 1, '트렌드 코리아 2023', '김난도 외', '미래의 창', DEFAULT, DEFAULT);

INSERT INTO BOOK
VALUES(SEQ_MNO.NEXTVAL, 'user02', 'pass02', '손흥민', '01032654587', 2, '모든 것은 기본에서 시작한다', '손웅정', '수오서재', DEFAULT, DEFAULT);

INSERT INTO BOOK
VALUES(SEQ_MNO.NEXTVAL, 'user03', 'pass03', '조규성', '01024576532', 3, '김훈 장편소설: 하얼빈', '김훈', '문학동네', DEFAULT, DEFAULT);

INSERT INTO BOOK
VALUES(SEQ_MNO.NEXTVAL, 'user04', 'pass04', '이승우', '01054236598', 4, '트렌드 코리아 2022', '김난도 외', '미래의 창', DEFAULT, DEFAULT);

COMMIT;

