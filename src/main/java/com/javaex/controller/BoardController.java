package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/* 리스트 게시판 첫화면 */
	//http://localhost:8888/mysite/board/boardlist
	@RequestMapping(value="/board/boardlist", method= {RequestMethod.GET, RequestMethod.POST})
	public String boardList(Model model) {
		System.out.println("BoardController.boardlist()");
		
		List<BoardVo> boardList = boardService.exeGetBoardList();
		
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	
	/* 게시판 읽기 */
	//http://localhost:8888/mysite/board/boardread ////////////////////////////////////////////////////////
	@RequestMapping(value="/board/boardread", method= {RequestMethod.GET, RequestMethod.POST})
	public String boardRead(@RequestParam(value="no") int no, Model model) {
		System.out.println("BoardController.boardRead()");
		
		BoardVo boardVo = boardService.exeGetReadOne(no);
		
		model.addAttribute("boardVo", boardVo);	
		
		return "board/read";
	}
	
	
	/* 게시판 쓰기폼 */
	//http://localhost:8888/mysite/board/boardwriteform
	@RequestMapping(value="/board/boardwriteform", method= {RequestMethod.GET, RequestMethod.POST})
	public String boardWriteform() {
		System.out.println("BoardController.boardWriteform()");
	
		return "board/writeForm";
	}
	
	
	/* 게시판 쓰기(등록) */
	//http://localhost:8888/mysite/board/boardwrite
	@RequestMapping(value="/board/boardwrite", method= {RequestMethod.GET, RequestMethod.POST})
	public String boardWrite() {
		System.out.println("BoardController.boardWrite()");
		
		boardService.exeboardWrite();
	
		return "";
	}
	
	
	/* 게시판 수정폼  */
	//http://localhost:8888/mysite/board/boardmodifyform
	@RequestMapping(value="/board/boardmodifyform", method= {RequestMethod.GET, RequestMethod.POST})
	public String boardModifyform() {
		System.out.println("boardController.boardModifyform()");

		return "board/modifyForm";
	}
	
	
	/* 게시판 수정  */
	//http://localhost:8888/mysite/board/boardmodify
	@RequestMapping(value="/board/boardmodify", method= {RequestMethod.GET, RequestMethod.POST})
	public String boardModify() {
		System.out.println("boardController.boardModify()");
		
		boardService.exeboardModify();

		return "";
	}
	
	
}
