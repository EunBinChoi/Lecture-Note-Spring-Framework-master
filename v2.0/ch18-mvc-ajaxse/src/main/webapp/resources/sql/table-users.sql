DROP TABLE USERS;
CREATE TABLE USERS (
  userName VARCHAR(20) NOT NULL,
  userAge NUMBER(20) NOT NULL CHECK ( (userAge BETWEEN 0 AND 200) AND LENGTH(userAge) <= 3 ),
  userGender VARCHAR(20) DEFAULT '남자' NOT NULL,
  userEmail  VARCHAR(20) NOT NULL 
);


ALTER TABLE USERS 
ADD CONSTRAINT userEmail_regex_ch CHECK (REGEXP_LIKE ( userEmail, '^[A-Za-z0-9\-\_\.]+\@[A-Za-z0-9\-]+(\.[A-Za-z]{2,3}){1,2}'));
  


-- 오라클  NOT NULL: '' 도 불가능, ' '은 가능

INSERT INTO USERS VALUES ('홍길동',  21, '남자', 'gildong@gmail.com');
INSERT INTO USERS VALUES ('김철수',  22, '남자', 'chulsu@gmail.com');
INSERT INTO USERS VALUES ('김영희',  23, '여자', 'younghee@gmail.com');
INSERT INTO USERS VALUES ('이순신',  24, '남자', 'soonshin@gmail.com');
INSERT INTO USERS VALUES ('둘리',   25, '남자', 'dooli@gmail.com');
INSERT INTO USERS VALUES ('마크',   26, '남자', 'mark@gmail.com');
INSERT INTO USERS VALUES ('줄리아', 27, '여자', 'julia@gmail.com');
INSERT INTO USERS VALUES ('안중근', 30, '남자', 'hero@gmail.com');


SELECT * FROM USERS;
COMMIT