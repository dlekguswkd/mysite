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

-- 바로 실행
flush privileges;
