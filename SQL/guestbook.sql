-- -----------------------------------------------------------
-- web 계정에서 실행
-- -----------------------------------------------------------
-- web_db 사용
use web_db;

-- table 삭제
drop table guestbook;

-- guestbook 테이블 생성
create table guestbook(
	no integer primary key auto_increment,
    name varchar(20),
    password varchar(20),
    content text,
	reg_date datetime
);

create table guestbook(
	no integer primary key auto_increment,
    name varchar(20),
    password varchar(20),
    content text,
	reg_date datetime,
    user_no integer not null,
    FOREIGN KEY(user_no) REFERENCES users(no)
);

-- 조회
select * from guestbook;

select 	no,
		name,
        password,
		content,
        reg_date
from guestbook;

select 	no,
		name,
        password,
		content,
        reg_date,
        user_no
from guestbook;

-- 등록
insert into guestbook (name, password, content) 
values ();

insert into guestbook (name, password, content, user_no) 
values ();


-- 삭제
delete from guestbook 
where password = '' 
and no = ;


CREATE TABLE guestbook (
    no INT PRIMARY KEY AUTO_INCREMENT,
    content TEXT,
    reg_date datetime,
    user_no INT NOT NULL,
    FOREIGN KEY (user_no) REFERENCES users(no)
);

insert into guestbook (content, user_no) 
values ('안녕하세요', 1);