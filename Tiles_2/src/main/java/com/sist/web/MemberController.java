package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	@GetMapping("member/join.do")
	public String member_joing() {
		return "member/join";
	}
}
