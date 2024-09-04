package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.TboardService;
import com.javaex.vo.TboardVo;

@Controller
public class TboardController {
	
	@Autowired
	private TboardService tboardService;

	//리스트만
	//http://localhost:8888/mysite/tboard/list
	@RequestMapping(value="/tboard/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("TboardController.list()");
		
		List<TboardVo> tboardList = tboardService.exeList();
//		System.out.println(tboardList);
//		System.out.println(tboardList.size());		// 187
		
							// 별명       주소
		model.addAttribute("tboardList", tboardList);
		return "tboard/list";
		
	}
	
	
	//리스트2 + 페이징
	//http://localhost:8888/mysite/tboard/list2
	@RequestMapping(value="/tboard/list2", method = {RequestMethod.GET, RequestMethod.POST})
	// 					   ~/list2?crtpage=~ 현재페이지	   페이지가 안올때(?crtpage=~) 안올때 디폴트값(?crtpage=1)
	public String list2(@RequestParam(value="crtpage", required=false, defaultValue="1") int crtPage, Model model) {
		System.out.println("TboardController.list2()");
		//System.out.println(crtPage);
		
		Map<String, Object> pMap = tboardService.exeList2(crtPage);
		System.out.println(pMap);
		
		model.addAttribute("pMap", pMap);
		return "tboard/list2";
		
	}
	
	
	//리스트3 + 페이징 + 검색
	//http://localhost:8888/mysite/tboard/list3?keyword=~
	@RequestMapping(value="/tboard/list3", method = {RequestMethod.GET, RequestMethod.POST})
	// 					   ~/list2?crtpage=~ 현재페이지	   페이지가 안올때(?crtpage=~) 안올때 디폴트값(?crtpage=1)
	public String list3(@RequestParam(value="crtpage", required=false, defaultValue="1") int crtPage, 
						@RequestParam(value="keyword", required=false, defaultValue="") String keyword,	
						Model model) {
		System.out.println("TboardController.list3()");
		//System.out.println(keyword);
		
		Map<String, Object> pMap = tboardService.exeList3(crtPage, keyword);
		
		model.addAttribute("pMap", pMap);
		return "tboard/list3";
		
	}
	
	
}
