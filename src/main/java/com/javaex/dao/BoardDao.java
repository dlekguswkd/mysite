package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/* 리스트 게시판 첫화면 */
	public List<BoardVo> getBoardList() {
		System.out.println("BoardDao.getBoardList()");
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		
		return boardList;
	}
	
	
	/* 게시판 읽기 */
	public BoardVo getRead(int no) {
		System.out.println("boardDao.getRead()");
		
		BoardVo boardVo = sqlSession.selectOne("board.selectRead", no);
		
		return boardVo;
	}

	
	/* 게시판 쓰기(등록) */
	public String getInsert() {
		System.out.println("boardDao.getInsert()");
		
//		int count = sqlSession.insert("board.insert", boardVo);		

		return "";
	}
	
	
	/* 게시판 수정 */
	public int boardModify() {
		System.out.println("BoardDao.boardModify()");
		
//		int count = sqlSession.update("board.update", boardVo);
		
		return 1;
	}

}
