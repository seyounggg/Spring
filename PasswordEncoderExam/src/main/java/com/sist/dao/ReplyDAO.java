package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;
/*
	데이터 저장 => mounted => render() => HTML에 데이터 출력
	--------------------------------------------------------
					|
				데이터가변경 : updateed => render() => HTML 변경 // 화면에 호출하는 애가 render()
								|
								data:{} 안의 변수값이 변경시에 호출
 */
@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	public List<ReplyVO> replyListData(int fno){
		return mapper.replyListData(fno);
	}
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}
	
	public void replyDelete(int no) {
		mapper.replyDelete(no);
	}
	
	public void replyUpdate(ReplyVO vo) {
		mapper.replyUpdate(vo);;
	}
}
