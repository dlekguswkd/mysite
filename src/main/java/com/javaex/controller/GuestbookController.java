package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;


@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	

	/* 방명록폼 (리스트도 보이기) */
	//http://localhost:8888/mysite/guestbook/guestbookform
	@RequestMapping(value="/guestbook/guestbookform",method= {RequestMethod.GET, RequestMethod.POST})
	public String guestbookForm(Model model) {
		System.out.println("GuestbookController.guestbookForm()");
		
		List<GuestbookVo> guestbookList = guestbookService.exeGetGuestList();
		
		model.addAttribute("guestbookList", guestbookList);
		
		//System.out.println("여기요"+guestbookList);
		
		return "guestbook/addList";
		
	}
	
	
	/* 방명록 등록 */
	//http://localhost:8888/mysite/guestbook/guestbookwrite?
	@RequestMapping(value="/guestbook/guestbookwrite",method= {RequestMethod.GET, RequestMethod.POST})
	public String guestbookWrite(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.guestbookWrite()");
		
		guestbookService.exeWriteGuestbook(guestbookVo);
		
		return "redirect:/guestbook/guestbookform";
	}
	
	/* 방명록 삭제폼 */
	//http://localhost:8888/mysite/guestbook/deleteform
	@RequestMapping(value="/guestbook/deleteform",method= {RequestMethod.GET, RequestMethod.POST}) 
	public String deleteForm() {
		System.out.println("GuestbookController.deleteForm()");
		
		//no값을 숨겨놔야한다  addList.jsp에서 줌
		
		return "guestbook/deleteForm";
	}
	
	
	/* 방명록 삭제 */
	//http://localhost:8888/mysite/guestbook/guestbookdelete?no=~&password=~
	@RequestMapping(value="/guestbook/guestbookdelete",method= {RequestMethod.GET, RequestMethod.POST}) 
	public String guestbookDelete(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("GuestbookController.guestbookDelete()");
		
		GuestbookVo deleteGuestbook = guestbookService.exeDeleteGuestbook(guestbookVo);
		
		return "redirect:/guestbook/guestbookform";
	}

	
}
