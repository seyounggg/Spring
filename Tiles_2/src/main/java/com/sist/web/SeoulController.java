package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.SeoulDAO;
import com.sist.vo.SeoulVO;

@Controller
public class SeoulController {
	@Autowired
	private SeoulDAO dao;
	private String[] names= {"","서울명소","서울자연","서울쇼핑"};
	
	@GetMapping("seoul/list.do")
	public String seoul_list(String page,String no,Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		if(no==null)
			no="1";
		int index=Integer.parseInt(no); // 1_location, 2_nature, 3_shop
		map.put("pNo", index);
		map.put("pStart", start);
		map.put("pEnd", end);
		
		List<SeoulVO> list=dao.seoulListData(map);
		
		map=new HashMap();
		map.put("pNo", index);
		int totalpage=dao.seoulTotalPage(map);
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("no", index);
		model.addAttribute("name", names[index]);
		return "seoul/list";
	}
	
	@GetMapping("seoul/detail.do")
	public String seoul_detail(int type,int no,Model model) {
		String[] tables= {"","seoul_location","seoul_nature","seoul_shop"};
		Map map=new HashMap();
		map.put("table_name", tables[type]);
		map.put("no", no);
		
		SeoulVO vo=dao.seoulDetailData(map);
		model.addAttribute("vo", vo);
		model.addAttribute("no", type);
		model.addAttribute("name", names[type]);
		return "seoul/detail";
	}
}
