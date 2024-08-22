package com.javaex.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	//@Autowired
	//private UserDao userDao;
	
	/* 회원가입 */
	public int exeJoinPerson() {	 
		System.out.println("UserService.exeJoinPerson()");
		
		int count = userDao.insertPerson(userVo);
		
		return count;
		
	}

}
