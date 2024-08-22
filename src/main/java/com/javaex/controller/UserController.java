package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;


@Controller
public class UserController {

	//필드
	@Autowired		
	private UserService userService;
	
	//생성자
	//메소드 gs
	//메소드 일반
	
	/* 회원가입폼 */
	//http://localhost:8888/mysite/user/joinform
	@RequestMapping(value="/user/joinform", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "user/joinForm";
	}
	
	
	/* 회원가입 */
	//http://localhost:8888/mysite/user/join?id=~&password=~&name=~&gender=~
	@RequestMapping(value="/user/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.join()");
		
		userService.exeJoinUser(userVo);
		
		return "/user/joinOk";
	}
	
	/* 회원가입 완료문구*/
	//http://localhost:8888/mysite/user/joinok
	@RequestMapping(value = "/user/joinok", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinok() {

		System.out.println("UserController.joinok()");

		return "/user/joinOk";
	}
	
	
}
