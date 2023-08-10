package com.sist.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	// join
	public void memberInsert(MemberVO vo) {
		mapper.memberInsert(vo);
	}
	
	// idcheck
	public int memberIdCheck(String id) {
		return mapper.memberIdCheck(id);
	}
	
	// login
	public MemberVO memberLogin(String id) {
		return mapper.memberLogin(id);
	}
	
//	public MemberVO memberIdCheck(String id,String pwd) {
//		MemberVO vo=new MemberVO();
//		int count=mapper.memberIdCheck(id);
//		if(count==0) {
//			vo.setMsg("noid");
//		} else {
//			MemberVO dbvo=mapper.memberLogin(id);
//			if(pwd.equals(dbvo.getPwd())) {
//				vo.setMsg("ok");
//				vo.setId(dbvo.getId());
//				vo.setName(dbvo.getName());
//				vo.setSex(dbvo.getSex());
//			} else {
//				vo.setMsg("nopwd");
//			}
//		}
//		return vo;
//	}
	
	
}
