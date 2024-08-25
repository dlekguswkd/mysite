package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/* 방명록폼 (리스트도 보이기) */
	public List<GuestbookVo> getGuestbookList() {
		System.out.println("GuestbookDao.exeGetGuestList()");
		
		List<GuestbookVo> guestbookList =sqlSession.selectList("guestbook.selectList");
		
		return guestbookList;
		
	}
	
	/* 방명록 등록 */
	public int insertGuestbook(GuestbookVo guestbookVo) {
		
		int count = sqlSession.insert("guestbook.insert", guestbookVo);
		
		return count;
	}

}
