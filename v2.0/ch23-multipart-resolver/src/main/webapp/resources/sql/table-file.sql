SELECT * FROM TAB;

DROP TABLE MP_FILE;
CREATE TABLE MP_FILE (
	FNO	        			 VARCHAR(10)       NOT NULL,            			 -- 파일 번호
	USERNAME	        	 VARCHAR(20)       NOT NULL,            			 -- 파일 게시자 이름
	TITLE	        	     VARCHAR(30)       NOT NULL,            			 -- 파일과 같이 올린 제목
	CONTENT	        	     VARCHAR(500)      NOT NULL,            			 -- 파일과 같이 올린 내용물
	ORIFILENAMES      		 VARCHAR(3000)     NOT NULL,			 			 -- 원본 파일 이름
	STOREDFILENAMES          VARCHAR(3000)     NOT NULL,             			 -- 저장된 파일 이름
	FSEPERATOR  			 VARCHAR(1)        DEFAULT '|' NOT NULL,           -- 파일 이름 구분자
	FCOUNT     				 VARCHAR(3) 	   DEFAULT '0' NOT NULL,           -- 파일 개수
	FTYPE       			 VARCHAR(1000)     DEFAULT '',         			 -- 파일 타입
	FSIZE       			 VARCHAR(1000)     DEFAULT '0',         			 -- 파일 크기
	-- PRIMARY KEY FNO
	CONSTRAINT MP_FILE_PK    PRIMARY KEY(FNO)
);

select * from MP_FILE;




DROP SEQUENCE SEQ_MP_FNO;
CREATE SEQUENCE SEQ_MP_FNO
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE NOCACHE;

SELECT * FROM MP_FILE;

COMMIT
