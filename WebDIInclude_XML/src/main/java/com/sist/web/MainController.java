package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class MainController {
	//객체주소를 받을지 여부 확인
	@Autowired
	private FoodDAO dao;
	
	//사용자 요청 처리
	@GetMapping("main/main.do")
	public String main_main(String cno,Model model) {
		if(cno==null)
			cno="1";
		Map map=new HashMap();
		map.put("cno", Integer.parseInt(cno));
		List<CategoryVO> list=dao.foodCategoryData(map);
		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "../main/home.jsp");
		return "main/main";
	}
}
