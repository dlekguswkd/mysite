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
		, user_no
		, content
        , filePath
        , orgName
        , saveName
		, fileSize
from gallery
;