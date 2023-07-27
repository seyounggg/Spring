package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.BoardVO;

import lombok.Getter;

@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	// list.do?page= → page로 받아야함(변수명 무조건 일치해야한다)
	// list
	@GetMapping("list.do")
	public String board_list(String page,Model model){
//		page를 int가 아니라 String으로 받는 이유는 가장 첫 페이지는 null값일 수 있기 때문에!!
//		Model model => 전송 객체 (request대신 사용)
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start",start);
		map.put("end", end);
		List<BoardVO> list=dao.boardListData(map);
		int totalpage=dao.boardTotalPage();
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("list",list);
		model.addAttribute("totalpage", totalpage);
		return "board/list";
	}

	// insert
	@GetMapping("insert.do")
	public String board_insert() {
		
		return "board/insert";
	}
	
	@PostMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:list.do";
	}
	// update
	
	// delete
	
	// detail
	/*
	 	class Model{
	 		HttpServletRequest request;
	 		public void addAtribute(String key,Object obj){
	 			requeest.setAttribute(key,obj);
	 		}
	 	}
	 */
	@GetMapping("detail.do")	// @requestgetParam("no") int no
	public String board_detail(int no,Model model) {
		// String no=request.getParameter("no") 이거 이제 없어!!
		BoardVO vo=dao.boardDetailData(no);
		
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	
	// find → 동적쿼리등장!!
}
