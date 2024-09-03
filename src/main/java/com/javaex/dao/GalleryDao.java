package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/* 갤러리 리스트폼 */
	public List<GalleryVo> getList() {
		System.out.println("GalleryService.getList()");
		
		List<GalleryVo> galleryList = sqlSession.selectList("gallery.selectList");
		
		return galleryList;
	}
	
	
	/* 갤러리 업로드 */
	public int insertFile(GalleryVo galleryVo) {
		System.out.println("GalleryDao.insertFile()");

		int count = sqlSession.insert("gallery.insert", galleryVo);

		return count;
	}

}
