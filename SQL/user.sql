-- ############################################
-- user 만들기
-- ############################################

-- -----------------------------------------------------------
-- web 계정에서 실행
-- -----------------------------------------------------------

use web_db;

-- user 테이블 생성 
create table users(
	no integer primary key auto_increment
    , id varchar(20) unique not null
    , password varchar(20) not null
    , name varchar(20)
    , gender varchar(10)
);


select no
	, id
	, password
	, name
    , gender
from users;

-- 로그인
select no
	 , name
from users
where id = '3'
and password = '3';


-- 넣기 (등록)
insert into users
values(null, 'jung', '1234', '정우성', 'male');

insert into users
values(null, 'lee', '5678', '이효리', 'female');


-- 수정
update users
set password = '1234',
	name = '정용화',
	gender = '남'
where id = 'jung'
;




-- 삭제
delete from users
where id = 'jung'
and password = '1234'
;

