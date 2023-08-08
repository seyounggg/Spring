package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.*;
import java.util.*;
import com.sist.vo.*;
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value="food/list_vue.do", produces = "text/plain;charset=utf-8")
	public String food_list_vue(String page) {
		String result="";
		try {
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			Map map=new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", curpage*12);
			
			List<FoodLocationVO> list=dao.foodListData(map);
			int totalpage=dao.foodTotalPage();
			
			// Vue로 보내려면 JSON 형식으로 보내야 하기떄문에 아래처럼 해야해!!
			// List => [] (List를 배열로 만들기!) => JSONArray
			// FoodLocationVO => {} => JSONObject
			// [{},{},{},...]
			JSONArray arr=new JSONArray();
			int i=0;
			for(FoodLocationVO vo:list) {
				JSONObject obj=new JSONObject();
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("score", vo.getScore());
				String poster=vo.getPoster();
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
				obj.put("poster",poster);
				// fno, name,poster,score
				/*
				 	{fno:1,name:"",poster:"",score:"5.9}
				 	이렇게 만든거임!
				 */
				if(i==0) {
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
				}
				arr.add(obj);
				i++;
			}
			result=arr.toJSONString();
		} catch (Exception e) {}
		return result;
	}
	/*
	    *    1. 일반 문자열 => NOID, NOPWD, OK => text/html
	    *    2. 데이터 묶음(JSON) => text/plain
	    *       List / VO
	    *            ---- {} => JSONObject
	    *       ---- [] => JSONArray
	    *       --------------------------------> jackson(Spring-Boot)
	    *    3. xml 전송 => text/xml
	    */
	@GetMapping(value="food/find_vue.do", produces = "text/plian;charset=utf-8")
	public String food_find(int page,String fd) {
		String result="";
		try {
			int curpage=page;
			Map map=new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", curpage*12);
			map.put("address", fd);
			
			List<FoodLocationVO> list=dao.foodFindData(map);
			int totalpage=dao.foodFindTotalPage(fd);
			
			final int BLOCK=5;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			// curpage => 1 - 5 => startPage = 1
			// curpage => 6 - 10 => startPage = 6
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			// JSON으로 변경
			int i=0;
			JSONArray arr=new JSONArray(); // list대신 사용!!
			for(FoodLocationVO vo:list) {
				JSONObject obj=new JSONObject();
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("score", vo.getScore());
				String poster = vo.getPoster();
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
				obj.put("poster", poster);
				if(i==0) { // 배열의 0번째 일때
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
					obj.put("startPage", startPage);
					obj.put("endPage", endPage);
				}
				i++;
				arr.add(obj);
			}
			result=arr.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
