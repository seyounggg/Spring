package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("food/food_list.do")
	public String food_list(int cno,Model model) {
		
		CategoryVO vo=dao.foodCategoryInfoData(cno);
		List<FoodVO> list=dao.foodListData(cno);
		
		model.addAttribute("cvo", vo);
		model.addAttribute("list", list); //request => 전송객체
		model.addAttribute("main_jsp", "../food/food_list.jsp");
		return "main/main";
	}
	
	@GetMapping("food/food_detail.do")
	public String food_detail(int fno,Model model) {
		FoodVO vo=dao.foodDetailData(fno);
		
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../food/food_detail.jsp");
		return "main/main";
	}
	
}
