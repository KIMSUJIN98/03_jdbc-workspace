-- ���̺����
DROP TABLE BOOK;

-- ����������
DROP SEQUENCE SEQ_MNO;

CREATE TABLE BOOK(
    MNO NUMBER PRIMARY KEY,                     -- ȸ����ȣ
    MID VARCHAR2(20) NOT NULL UNIQUE,           -- ȸ�����̵�
    MPWD VARCHAR2(20) NOT NULL,                 -- ȸ����й�ȣ
    MNAME VARCHAR2(20) NOT NULL,                -- ȸ����
    MPHONE VARCHAR2(11) NOT NULL,               -- ȸ������ó
    BNO NUMBER NOT NULL UNIQUE,                 -- �뿩������ȣ
    BNAME VARCHAR2(50) NOT NULL,                -- �뿩������
    WNAME VARCHAR2(30) NOT NULL,                -- �۰���
    PNAME VARCHAR2(30) NOT NULL,                -- ���ǻ��
    RENTDATE DATE DEFAULT SYSDATE NOT NULL,     -- �뿩��
    ENDDATE DATE DEFAULT SYSDATE+7 NOT NULL     -- �ݳ���
);

CREATE SEQUENCE SEQ_MNO
NOCACHE;

INSERT INTO BOOK
VALUES(SEQ_MNO.NEXTVAL, 'admin', 'admin1', '�����', '01050499858', 0, '���α׷� ��뼳��', '������', '������ ��', DEFAULT, DEFAULT);

INSERT INTO BOOK
VALUES(SEQ_MNO.NEXTVAL, 'user01', 'pass01', '�����', '01050499858', 1, 'Ʈ���� �ڸ��� 2023', '�賭�� ��', '�̷��� â', DEFAULT, DEFAULT);

INSERT INTO BOOK
VALUES(SEQ_MNO.NEXTVAL, 'user02', 'pass02', '�����', '01032654587', 2, '��� ���� �⺻���� �����Ѵ�', '�տ���', '��������', DEFAULT, DEFAULT);

INSERT INTO BOOK
VALUES(SEQ_MNO.NEXTVAL, 'user03', 'pass03', '���Լ�', '01024576532', 3, '���� ����Ҽ�: �Ͼ��', '����', '���е���', DEFAULT, DEFAULT);

INSERT INTO BOOK
VALUES(SEQ_MNO.NEXTVAL, 'user04', 'pass04', '�̽¿�', '01054236598', 4, 'Ʈ���� �ڸ��� 2022', '�賭�� ��', '�̷��� â', DEFAULT, DEFAULT);

COMMIT;

