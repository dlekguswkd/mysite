-- ############################################
-- mysite 만들기
-- ############################################

-- --------------------------------------------
-- root 계정에서 실행
-- --------------------------------------------

--  users 계정생성 
create user 'web'@'%' identified by 'web' ;

--  mysite 권한 부여
grant all privileges on web_db.* to 'web'@'%';

--  mysite_db 생성
create database web_db
	default character set utf8mb4	-- • 이모티콘사용 케릭터셋
	collate utf8mb4_general_ci		-- • 정렬규칙
	default encryption='n'			-- • 암호화 no (기본값 생략가능)
;

-- 데이터베이스 조회
show databases;

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


-- 넣기 (등록)
insert into users
values(null, 'jung', '1234', '정우성', '남');

insert into users
values(null, 'lee', '5678', '이효리', '여');


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

