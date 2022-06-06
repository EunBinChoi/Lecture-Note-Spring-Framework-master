DROP TABLE tblMember;

CREATE TABLE tblMember (
	id VARCHAR(20) NOT NULL,
	pwd VARCHAR(20) NOT NULL,
	name VARCHAR(6)  DEFAULT NULL,
	num1 VARCHAR(6)  DEFAULT NULL,
--	주민등록번호 앞자리
	num2 VARCHAR(7)  DEFAULT NULL,
--	주민등록번호 뒷자리
	email VARCHAR(30) DEFAULT NULL,
	phone VARCHAR(30) DEFAULT NULL,
	zipcode VARCHAR(5)  DEFAULT NULL,
	address VARCHAR(60)  DEFAULT NULL,
	job VARCHAR(30) DEFAULT NULL,
	PRIMARY KEY (id)
);


INSERT INTO tblMember
VALUES ('admin', '1234', 'eunbin', 
'901234', '2000000', 'qwe@gmail.com', '010-1234-5678',
'1234', 'seoul', 'lecturer');


DROP TABLE tblZipcode;

CREATE TABLE tblZipcode (
  zipcode char(5) NOT NULL,
  address char(50) DEFAULT NULL
);


select * from tblZipcode;
select * from tblZipcode where address like '%강북%';

COMMIT