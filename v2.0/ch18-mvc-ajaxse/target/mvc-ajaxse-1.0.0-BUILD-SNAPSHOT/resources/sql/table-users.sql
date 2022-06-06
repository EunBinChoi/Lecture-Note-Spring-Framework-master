DROP TABLE USERS;
CREATE TABLE USERS (
  userName VARCHAR(20),
  userAge NUMBER(20),
  userGender VARCHAR(20),
  userEmail VARCHAR(50)
);

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