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
    name varchar(20) not null,
    password varchar(20) not null,
    content text,
	reg_date datetime
);


-- 조회
select * from guestbook;

select 	no,
		name,
        password,
		content,
        reg_date
from guestbook;


-- 등록
insert into guestbook
values (null, 'aaa', 'aaa', '안녕하세요', now());

insert into guestbook
values (null, 'bbb', 'bbb', 'b입니다', now());


-- 삭제
delete from guestbook 
where name = 'aaa' 
and password = 'aaa';
