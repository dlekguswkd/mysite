-- ############################################
-- board 만들기
-- ############################################
-- -----------------------------------------------------------
-- web 계정에서 실행
-- -----------------------------------------------------------
use web_db;

drop table board;

create table board (
	no integer primary key auto_increment
    , title varchar(500) not null
    , content text
    , hit integer default 0
    , reg_date datetime not null
    , user_no integer not null
	, foreign key (user_no) references users(no)
);


-- 조회
select * from board;

-- 리스트 
select  no
		, title
		, content
        , hit
        , reg_date
        , user_no
from board
;

-- 내가 쓴글 리스트
select  b.no
		, b.title
		, b.content
        , b.hit
        , b.reg_date
        , b.user_no
        , u.name
from board b, users u
where b.user_no = u.no
order by b.no
;

-- 하나 글읽기
select  b.no
		, b.title
		, b.content
        , b.hit
        , b.reg_date
        , b.user_no
        , u.name
from board b, users u
where b.user_no  = u.no
and b.no  = 1
;

-- 데이터 추가
insert into board (title, content, reg_date, user_no)
values('aaa', 'aaa', now(), 1)
;

insert into board (title, content, reg_date, user_no)
values('bbb', 'bbb', now(), 2)
;

insert into board (title, content, reg_date, user_no)
values('ccc', 'ccc', now(), 3)
;

-- 등록 
insert into board
values( null, 'aaa', '111', null, now(), 1 )
;

-- 수정
update board
set title = '제목변경'
    , content= '뭐라고 변경할까'
where no = 1;


--  삭제
delete from board
where no = '2'
;

-- 조회수 증가
update board
set hit = hit+1
where no = 1;

////////////////////////////////////////
 -- 조회수 증가
DELIMITER //

CREATE PROCEDURE increaseHitCount(IN postHit INT)
BEGIN
    UPDATE board
    SET hit = hit + 1
    WHERE no = postHit;
END //

DELIMITER ;

CALL increaseHitCount(1);
