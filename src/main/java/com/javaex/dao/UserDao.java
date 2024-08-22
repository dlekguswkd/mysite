package com.javaex.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository 
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;

	//유저 회원가입
	public int insertPerson(UserVo userVo) {
		
		System.out.println("UserDao.insertPerson()");
		int count = sqlSession.insert("user.insert", userVo);
		
		return count;
		
	}
}
