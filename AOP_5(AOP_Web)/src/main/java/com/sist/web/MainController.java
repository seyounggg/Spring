package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.service.*;
import com.sist.vo.*;

@Controller
public class MainController {
	@Autowired
	private FoodService service;
	
	@GetMapping("main/main.do")
	public String main_main(Model model) {
		List<CategoryVO> cList=service.foodCategoryData();
		
		model.addAttribute("cList", cList);
		model.addAttribute("main_jsp", "home.jsp");
		return "main/main";
	}
}
