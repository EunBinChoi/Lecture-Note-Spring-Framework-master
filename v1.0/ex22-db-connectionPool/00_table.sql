DROP TABLE MEMBER;

CREATE TABLE MEMBER(
	memId VARCHAR(10) PRIMARY KEY,
	memPw VARCHAR(10),
	memMail VARCHAR(20),
	memPhone1 VARCHAR(3), -- 010
	memPhone2 VARCHAR(4), -- 1234
	memPhone3 VARCHAR(4) -- 5678
);

SELECT * FROM MEMBER;

COMMIT