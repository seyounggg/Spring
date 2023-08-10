package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value="food/food_category_vue.do",produces="text/plain;charset=utf-8")
	public String food_category(int cno) throws Exception{
		Map map=new HashMap();
		map.put("cno", cno);
		List<CategoryVO> list=dao.foodCategoryListData(map);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value="food/food_category_info_vue.do",produces = "text/plain;charset=utf-8")
	public String food_category_info(int cno) throws Exception {
		CategoryVO vo=dao.foodCategoryInfoData(cno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value="food/food_list_vue.do", produces = "text/plain;charset=utf-8")
	public String food_list(int cno) throws Exception {
		List<FoodVO> list=dao.foodListData(cno);
		for(FoodVO vo:list) {
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
			
			String address=vo.getAddress();
			address=address.substring(0,address.indexOf("지번"));
			vo.setAddress(address);
		}
		//json 변경
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
}
