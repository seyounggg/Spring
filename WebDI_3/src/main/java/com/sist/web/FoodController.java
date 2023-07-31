package com.sist.web;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@RequestMapping("food/category.do")
	// 첫 실행 페이지에서는 카테고리 번호를 주지 않기 떄문에 String으로 설정을 해야한다.
	public String food_category(String cno,Model model) {
		if(cno==null) cno="1";
		Map map=new HashMap();
		map.put("cno", Integer.parseInt(cno));
		List<CategoryVO> list=dao.categoryListData(map);
		
		model.addAttribute("list",list);
		return "food/category";
	}
}
