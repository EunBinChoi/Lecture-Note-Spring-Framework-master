DROP TABLE MEMBER;

CREATE TABLE MEMBER(
	memId VARCHAR(10) PRIMARY KEY,
	memPw VARCHAR(10),
	memMail VARCHAR(20),
	memPhone1 NUMBER(3),
	memPhone2 NUMBER(4),
	memPhone3 NUMBER(4)
);

SELECT * FROM MEMBER;

COMMIT