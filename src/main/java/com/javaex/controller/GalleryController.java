package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;

	/* 갤러리 리스트폼 */
	//http://localhost:8888/mysite/gallery/listform
	@RequestMapping (value ="/gallery/listform", method = { RequestMethod.GET, RequestMethod.POST })
	public String listForm(Model model) {
		System.out.println("GalleryController.listForm()");

		List<GalleryVo> galleryList = galleryService.exeGetList();

		model.addAttribute("galleryList", galleryList);

		return "gallery/list";
	}
	
	
	/* 갤러리 업로드 */
	//http://localhost:8888/mysite/gallery/upload
	@RequestMapping(value="/gallery/upload", method= {RequestMethod.GET, RequestMethod.POST})
	public String galleryUpload(@RequestParam(value="file") MultipartFile file, Model model) {	// 파일 보낼때
		System.out.println("GalleryController.galleryUpload()");
		//System.out.println(file.getOriginalFilename());
		
		String saveName = galleryService.galleryUpload(file);
		// System.out.println(saveName);
		
		model.addAttribute("saveName", saveName);
		
		return "redirect:/gallery/listform";
	}
	

}
