package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<boardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	public void boardInsert(boardVO vo) {
		mapper.boardInsert(vo);
	}
	
	public boardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void boardReplyInsert(boardVO vo, int root) {
		boardVO pvo=mapper.boardParentInfoData(root);
		mapper.boardGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		mapper.boardReplyInsert(vo);
		mapper.boardDepthIncrement(root);
	}
}
