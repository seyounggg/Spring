package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
	
	@GetMapping("food/food_before_detail.do")
	public String food_before_detail(int fno,RedirectAttributes ra,
										HttpServletResponse response) {
		/*
		 	Model : forward일때 값 전송
		 	RedirectAttribute : sendRedirect일때 값 전송
		 */
		Cookie cookie=new Cookie("food_"+fno, String.valueOf(fno));
		// cookie의 매개변수가(String, String)이라서 int인 fno를 그대로 사용하면 오류 발생함.
		// cookie는 String으로만 저장이 가능하다(데이터값)
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		// 브라우저로 전송
		response.addCookie(cookie);
		ra.addAttribute("fno", fno); // 값을 출력하면서 이동
		//ra.addFlashAttribute(attributeValue); - 값을 감추고 이동
		return "redirect:../food/food_detail.do";
	}

	
	@GetMapping("food/food_detail.do")
	public String food_detail(int fno,Model model) {
		FoodVO vo=dao.foodDetailData(fno);
		
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../food/food_detail.jsp");
		return "main/main";
	}
	
}
