package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@RestController
@RequestMapping("board/")
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	// ModelAttribute
	// 아래처럼 Spring에서 스크립트 작성하면 안돼!! 크롬에서만 가능하기 때문에
	// 자바스크립트에 데이터를 전송할 경우에만 사용하기!
	/*	Ajax, Vue, React => BoardVO List
	 * 						|		| JSONArray
							JSONObjcet
	 */						
	@PostMapping(value="update_ok.do",produces = "text/html;charset=UTF-8")
	public String board_update_ok(BoardVO vo) {
		String result="";
		boolean bCheck=dao.boardUpdate(vo);
		if(bCheck==true) {
			result="<script>location.href=\"detail.do?no="+vo.getNo()+"\"</script>";
		} else {
			result="<script>"
					+ "alert(\"비밀번호가 틀립니다\");"
					+ "history.back()"
					+ "</script>";
		}
		return result;
	}
}
