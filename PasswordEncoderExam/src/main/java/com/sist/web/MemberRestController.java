package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.dao.*;

// Security 5 ==> 반드시 BCryptPasswordEncoder를 추가해야함!!

@RestController
public class MemberRestController {
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private MemberDAO dao;
	@PostMapping(value="member/join_ok.do", produces = "text/plain;charset=UTF-8")
	/*
	 * axios.post(),{
	 * 	params:{
	 * 		여기 값이 vo에 있는 값이라서 vo로 받는거임
	 * 	}
	 * }
	 */
	public String member_join(MemberVO vo) {
		// 디버깅(값이 들어오는징?)
		System.out.println("id:"+vo.getId());
		System.out.println("pwd:"+vo.getPwd());
		System.out.println("name:"+vo.getName());
		System.out.println("sex:"+vo.getSex());
		
		String result="";
		try {
			String en=encoder.encode(vo.getPwd());
			// 비밀번호 암호화
			// 같은 비밀번호가 입력되더라도, 암호화는 다른 값으로 들어간다!!
			// 회원가입 시 비밀번호를 1234로 작성하는 사람이 여러명이어도 db에 등록되는 암호화된 pwd는 다 다르다!!
			vo.setPwd(en);
			dao.memberInsert(vo);
			
			result="yes";
		} catch (Exception e) {
			e.printStackTrace();
			result="no";
		}
		return result;
	}
	
	@PostMapping(value="member/login_ok.do", produces = "text/plain;charset=UTF-8")
	public String member_login(String id, String pwd, HttpSession session) {
		//											  ------------------- 매개변수로 session을 선언한다
		// 선언하면 DispatcherServlet에 의해 값이 주입된다.
		// 필요한 객체가 있으면, 매개변수를 이용해서 받으면 된다
		// 매개변수에는 순서가 없어서 순서는 중요하게 생각하지 않아도 된다.
		// 사용자 전송(요청) => request.getParameter()
		// 받을 수 있는 클래스 : 내장객체 모두 가능, Model(전송객체) : forward상태 에서만, redirect: RedirectAttributes!
		// @RestController는 다른 언어와 연결할 때 사용(JavaScript, Kotlin)
		// JSON, <script>, 일반 문자열 ... => @ResponseBody 에서 @RestController가 됨
		String result="";
		int count=dao.memberIdCheck(id);
		if(count==0) {
			result="noid";
		} else {
			MemberVO vo=dao.memberLogin(id);
			if(encoder.matches(pwd, vo.getPwd())) {
			// 암호화된 pwd와 실제 입력된 pwd가 같은지 확인 => matches (복호화)
				// 로그인
				result="ok";
				// session 저장
				session.setAttribute("id", vo.getId());
				session.setAttribute("name", vo.getName());
				session.setAttribute("sex", vo.getSex());
			} else {
				// 비밀번호 틀린 상태
				result="nopwd";
			}
		}
		return result;
	}
}
