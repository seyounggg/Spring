package com.sist.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * https://www.kobis.or.kr/kobis/business/main/
 * 
 * searchMainDailyBoxOffice.do
 * searchMainRealTicket.do
 * searchMainDailySeatTicket.do
 */
@RestController
public class MovieRestController {
	private String[] urls= {"","https://www.kobis.or.kr/kobis/business/main/searchMainDailyBoxOffice.do",
			"https://www.kobis.or.kr/kobis/business/main/searchMainRealTicket.do",
			"https://www.kobis.or.kr/kobis/business/main/searchMainDailySeatTicket.do"};
	
	@GetMapping(value="movie/movie_rank_vue.do",produces = "text/plain;charset=UTF-8")
/*
	1. hml 전송 : text/html
	2. xml 전송 : text/xml
	3. JSON 전송 : text/plain
	
	vo ===> {} ---> JSONObject
	List ===> [[},{},{}...] ===> JSONArray
 */
	public String movie_rank(int no) {
		return "";
	}
	
}
