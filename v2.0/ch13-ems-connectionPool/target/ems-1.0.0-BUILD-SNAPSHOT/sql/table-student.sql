drop table student;
create table student (
	sNum    varchar(50),
	sId     varchar(50),
	sPw     varchar(50),
	sName   varchar(50),
	sAge    number,
	sGender varchar(50),
	sMajor  varchar(50),
	primary key (sNum)

);
select * from student;