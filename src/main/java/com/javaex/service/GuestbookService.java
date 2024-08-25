package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	/* 방명록폼 (리스트도 보이기) */
	public List<GuestbookVo> exeGetGuestList() {
		System.out.println("GuestbookService.exeGetGuestList()");
		
		List<GuestbookVo> guestbookList = guestbookDao.getGuestbookList();
		
		return guestbookList;
	}
	
	/* 방명록 등록 */
	public int exeWriteGuestbook(GuestbookVo guestbookVo) {
		
		int count = guestbookDao.insertGuestbook(guestbookVo);
		
		return count;
	}

}
