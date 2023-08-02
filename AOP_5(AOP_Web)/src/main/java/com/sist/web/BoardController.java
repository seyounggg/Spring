package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	// 실무에서는 service 인터페이스를 거쳐서 작성해야한다!!
	
	@RequestMapping("list.do")
	public String board_list(String page,Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start",start);
		map.put("end", end);
		List<boardVO> list=dao.boardListData(map);
		int totalpage=dao.boardTotalPage();
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("list",list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("main_jsp", "../board/list.jsp");
		return "main/main";
	}
	
	@GetMapping("insert.do")
	public String board_insert(Model model) {
		
		model.addAttribute("main_jsp", "../board/insert.jsp");
		return "main/main";
	}
	@PostMapping("insert_ok.do")
	public String board_insert_ok(boardVO vo) {
		dao.boardInsert(vo);
		return "redirect:../board/list.do";
	}
	
	@GetMapping("detail.do")
	public String board_detail(int no,Model model) {
		boardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		// 문장 분석
		model.addAttribute("main_jsp", "../board/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("reply.do")
	public String board_reply(int pno,Model model) {
		
		model.addAttribute("pno", pno);
		model.addAttribute("main_jsp", "../board/reply.jsp");
		return "main/main";
	}
	
	@PostMapping("reply_ok.do")
	public String board_reply_ok(boardVO vo,int pno) {
		dao.boardReplyInsert(vo, pno);
		return "redirect:../board/list.do";
	}
}
