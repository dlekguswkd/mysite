package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;


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
	public String join(@ModelAttribute UserVo userVo) {		//묶이면 @ModelAttribute
		System.out.println("UserController.join()");
		
		userService.exeJoinUser(userVo);
		
		// 포워드
		return "user/joinOk";
	}
	
	/* 회원가입 완료문구*/
	//http://localhost:8888/mysite/user/joinok
	@RequestMapping(value = "/user/joinok", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinok() {

		System.out.println("UserController.joinok()");

		return "user/joinOk";
	}
	
	
	// -----------------------------------------------------------------------------
	/* 로그인폼 */
	//http://localhost:8888/mysite/user/loginform
	@RequestMapping(value = "/user/loginform", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController.loginForm()");

		return "user/loginForm";
	}
	
	/* 로그인 */
	//http://localhost:8888/mysite/user/login?id=~&password=~
	@RequestMapping(value="/user/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
											// HttpSession은 세션 저장하는 
		System.out.println("UserController.login()");
		
		UserVo authUser = userService.exeLogin(userVo);
		
		// 이게 로그인임(세션영역에 저장) (session 3시간 버티는 공간에 넣어둠) 한바퀴 돌아도 3시간은 남아있는것
		session.setAttribute("authUser", authUser);
		
		//메인페이지로 리다이렉트
		return "redirect:/main";
	}
	
	/* 로그아웃 */
	//http://localhost:8888/mysite/user/logout
	@RequestMapping(value="/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");
		
		//얘는 공간자체를 없애버림 () 로그아웃됨 session이 사라짐
		session.invalidate();
		/* authUser을 없애버리는 동작 (특정데이터만 없애버림)
		session.removeAttribute("authUser");
		*/
		
		return "redirect:/main";
	}
	
	
	// -----------------------------------------------------------------------------
	
	/* 수정폼 */
	//http://localhost:8888/mysite/user/modifyform
	@RequestMapping(value="/user/modifyform", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyform(HttpSession session, Model model) {
		System.out.println("UserController.modifyform()");
		
	    //로그인된 사용자 정보 가져오기
	    UserVo authUser = (UserVo) session.getAttribute("authUser");
	    
	    int no = authUser.getNo();

	    model.addAttribute("userVo", no);
	    //System.out.println(authUser);
		
		return "user/modifyform";
		
	}
	
	/* 수정 */
	//http://localhost:8888/mysite/user/modify
	@RequestMapping(value="/user/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController.modify()");
		
		UserVo authUser = userService.exeModify(userVo);
		
		session.setAttribute("authUser", authUser);
		
		return "redirect:/main";
		
	}
	
	
	
	
	
}
