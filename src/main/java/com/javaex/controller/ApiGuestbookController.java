package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	// 전체 리스트 가져오기
	//http://localhost:8888/mysite/guestbook/ajaxindex
	@ResponseBody	// 리턴에 있는 데이터를 json으로 바꿔서 응답문서의 body에 넣는다 (응답문서 바디에 넣어라 -> 글자로 들어감)
	@RequestMapping(value="/api/guestbook/list", method= {RequestMethod.GET, RequestMethod.POST})
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestbookVo> guestbookList = guestbookService.exeGetGuestList();	//게스트북서비스에서 가져옴(뒤에 주루룩)(컨트롤꺼 가져옴)
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	
	// 방명록 등록
	//http://localhost:8888/mysite/guestbook/ajaxindex 에서 등록버튼 눌렀을때 일어나는일
	@ResponseBody		// 응답을 json
	@RequestMapping(value="/api/guestbook/write", method= {RequestMethod.GET, RequestMethod.POST})
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.write()");
		
		// int count = guestbookService.exeWriteGuestbook(guestbookVo);		//게스트북서비스에서 가져옴(뒤에 주루룩)(컨트롤꺼 가져옴)
																			// 이걸로하면 regDate가 안옴 문제가 생김
		
		System.out.println(guestbookVo);		// 데이터 3개짜리 vo
		// 가져오기
		GuestbookVo gVo = guestbookService.exeAddandGuest(guestbookVo);
		System.out.println(gVo);		// 데이터 5개짜리 vo
		
//		System.out.println(guestbookVo);
//		System.out.println(count);
		
		return gVo;
	}

	
	// 방명록 삭제
	//http://localhost:8888/mysite/guestbook/remove
	@ResponseBody
	@RequestMapping(value="/api/guestbook/remove", method= {RequestMethod.GET, RequestMethod.POST})
	public int remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.remove()");
		//System.out.println(guestbookVo);
		
		int count = guestbookService.exeDeleteGuestbook(guestbookVo);
		System.out.println(count);
		return count;
	}
	
	
}
