package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.service.RecipeService;
import com.sist.vo.RecipeVO;

import java.util.*;

@RestController
public class RecipeRestController {
	@Autowired
	private RecipeService service;
	
	@GetMapping(value = "recipe/list_vue.do", produces = "text/plain;charset=utf-8")
	public String recipe_list_vue(int page) {
		String result="";
		try {
			int curpage=page;
			
			Map map=new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", curpage*12);
			
			List<RecipeVO> list=service.recipeListData(map);
			int totalpage=service.recipeTotalPage();
			
			final int BLOCK=10;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			int i=0;
			JSONArray arr=new JSONArray();
			for(RecipeVO vo:list) {
				JSONObject obj=new JSONObject();
				//	private int no,hit,title,poster,chef,link;
				obj.put("no", vo.getNo());
				obj.put("hit", vo.getHit());
				obj.put("title", vo.getTitle());
				obj.put("chef", vo.getChef());
				obj.put("poster", vo.getPoster());
				
				if(i==0) {
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
					obj.put("startPage", startPage);
					obj.put("endPage", endPage);
				}
				arr.add(obj);
				i++;
			}
			result=arr.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
