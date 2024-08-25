package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository 
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;	//pom.xml에 mysql, mybatis 추가
									//application.properties에 datasource, mybatis 추가
									//resources에 mybatis, configuration, mappers, user.xml 추가

	//유저 회원가입
	public int insertUser(UserVo userVo) {
		System.out.println("UserDao.insertUser()");
		
						//     정해진이름insert (총 5가지있음)
						// user.xml에 user에  id이름
		int count = sqlSession.insert("user.insert", userVo);
		
		return count;
		
	}
	
	
	//로그인 
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao.selectUser()");
		
		//     인증된유저 (로그인에 성공한 사람들) 담아둔것
		UserVo authUser = sqlSession.selectOne("user.selectByIdPw", userVo);
		
		return authUser;
	}
	
	
	//수정 
	public UserVo updateUser(UserVo userVo) {
		System.out.println("UserDao.updateUser()");
		
		sqlSession.update("user.updateUser", userVo);
		
		return userVo;
	}
	
	
}
