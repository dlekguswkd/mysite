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
	//http://localhost:8888/mysite/guestbook/guestbookForm
	@RequestMapping(value="/guestbook/guestbookForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String guestbookForm(Model model) {
		System.out.println("GuestbookController.guestbookForm()");
		
		List<GuestbookVo> guestbookList = guestbookService.exeGetGuestList();
		
		model.addAttribute("guestbookList", guestbookList);
		
		return "guestbook/addList";
	}
	
	
	/* 방명록 등록 */
	//http://localhost:8888/mysite/guestbook/guestbookWrite?
	@RequestMapping(value="/guestbook/guestbookWrite", method= {RequestMethod.GET, RequestMethod.POST})
	public String guestbookWrite(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.guestbookWrite()");
		
		guestbookService.exeWriteGuestbook(guestbookVo);
		
		return "redirect:/guestbook/addList";
		
	}
	
	/* 방명록 삭제 폼 */
	//http://localhost:8888/mysite/guestbook/deleteform
	@RequestMapping(value="/guestbook/deleteform", method = { RequestMethod.GET, RequestMethod.POST }) 
	public String deleteForm() {
		
		System.out.println("GuestbookController.deleteForm()");
		
		return "guestbook/deleteForm";
	}

}
