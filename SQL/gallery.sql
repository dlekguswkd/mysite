-- ############################################
-- gallery 만들기
-- ############################################
-- -----------------------------------------------------------
-- web 계정에서 실행
-- -----------------------------------------------------------
use web_db;

show tables;

drop table gallery;

create table gallery (
	no integer primary key auto_increment
    , user_no integer
	, content varchar(1000)
	, filePath varchar(500)
    , orgName varchar(200)
    , saveName varchar(500)
    , fileSize integer 
   , foreign key (user_no) references users(no)
);

-- 조회
select * from gallery;

-- 리스트 
select  no
		, user_no as userNo
		, content
        , filePath
        , orgName
        , saveName
		, fileSize
from gallery
;



-- 유저 정보 조인
select g.no
      , g.user_no as userNo
      , u.name
      , g.content
      , g.filePath
      , g.orgName
      , g.saveName
      , g.fileSize
from gallery g
inner join users u
on g.user_no = u.no
;

select g.no
      , g.user_no as userNo
      , u.name
      , g.content
      , g.filePath
      , g.orgName
      , g.saveName
      , g.fileSize
from gallery g
inner join users u
on g.user_no = u.no
where g.no = 1
;


-- 데이터 추가 ?
insert into gallery (user_no, content, file_path, org_name, save_name, file_size) 
values (1, '강호동', 'C:\javaStudy\upload\~.jpg', 'Gangho-dong.jpg', '~.jpg', 49876);


-- 삭제
delete from gallery
where no = 4;



////////////////////////////////////////////////////////////