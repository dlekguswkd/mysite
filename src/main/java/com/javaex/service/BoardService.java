package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	/* 리스트 게시판 첫화면 */
	public List<BoardVo> exeGetBoardList() {
		System.out.println("BoardService.exeGetBoardList()");
		
		List<BoardVo> boardList = boardDao.getBoardList();
		
		return boardList;
		
	}
	
	
	/* 게시판 읽기 */
	public BoardVo exeGetReadOne(int no) {
		System.out.println("boardService.exeGetReadOne()");
		
		BoardVo boardVo = boardDao.getRead(no);
		
		return boardVo;
	}
	
	
	/* 게시판 쓰기(등록) */
	public String exeboardWrite() {
		System.out.println("boardService.exeboardWrite()");
		
		boardDao.getInsert();
		
		return "";
	}
	
	
	/* 게시판 수정  */
	public int exeboardModify() {
		System.out.println("boardService.exeboardModify()");
		
		boardDao.boardModify();
		
		return 1;
	}
	

}
