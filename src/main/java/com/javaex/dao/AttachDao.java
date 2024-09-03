package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.AttachVo;

@Repository
public class AttachDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/* 파일 db에 저장 */
	public int insertFile(AttachVo attachVo) {
		System.out.println("AttachDao.insertFile()");

		int count = sqlSession.insert("attach.insert", attachVo);

		return count;
	}

}
