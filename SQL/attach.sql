-- ############################################
-- AttachVo 만들기
-- ############################################
-- -----------------------------------------------------------
-- web 계정에서 실행
-- -----------------------------------------------------------
use web_db;

drop table attach;

create table attach (
	no integer primary key auto_increment
    , orgName varchar(200)
    , saveName varchar(500)
    , fileSize integer 
    , filePath varchar(500)
);


-- 조회
select * from attach;


-- 리스트
select no
      , orgName
      , saveName
      , fileSize
      , filePath
from attach
;

