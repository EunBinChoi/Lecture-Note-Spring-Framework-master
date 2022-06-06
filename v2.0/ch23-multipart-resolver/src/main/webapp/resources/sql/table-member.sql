SELECT * FROM TAB;

DROP TABLE MP_MEMBER;
CREATE TABLE MP_MEMBER (
	MID         VARCHAR(20)   NOT NULL,				-- 회원 아이디 
	MPWD        VARCHAR(20)   NOT NULL,				-- 회원 비밀번호
	MNAME       VARCHAR(20)   DEFAULT '',			-- 회원 이름
	MEMAIL      VARCHAR(20)   DEFAULT '',			-- 회원 이메일
	MPHONE      VARCHAR(20)   DEFAULT '',			-- 회원 핸드폰 번호
	MUPDATE     VARCHAR(20)   DEFAULT '',			-- 회원 정보 수정일
	MREGDATE    VARCHAR(20)   DEFAULT '',			-- 회원 등록일
	-- PRIMARY KEY ID
	CONSTRAINT MP_MEMBER_PK PRIMARY KEY(MID)
);

-- INSERT INTO MEMBER 데이터 3개 넣기
INSERT ALL
INTO MP_MEMBER VALUES('a', 'a', 'a', 'a@naver.com', '010-0000-0000', NULL, SYSDATE)
INTO MP_MEMBER VALUES('b', 'b', 'b', 'b@naver.com', '010-0000-0000', NULL, SYSDATE)
INTO MP_MEMBER VALUES('c', 'c', 'c', 'c@naver.com', '010-0000-0000', NULL, SYSDATE)
SELECT * FROM DUAL;

SELECT * FROM MP_MEMBER;

COMMIT
