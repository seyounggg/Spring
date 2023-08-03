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
	
	// update
	public boardVO boardUpdateData(int no) {
		
		return mapper.boardDetailData(no);
	}
	
	public boolean boardUpdate(boardVO vo) {
		boolean bCheck=false;
		String db_pwd=mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck=true;
			mapper.boardUpdate(vo);
		}
		
		return bCheck;
	}
	
	// delete
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public boolean boardDelete(int no,String pwd) {
		boolean bCheck=false;
		// getPwd
		String db_pwd=mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
			// 삭제가 가능한 게시글인가요? (depth로 확인가능!)
			boardVO vo=mapper.boardInfoData(no);
			if(vo.getDepth()==0) {
				mapper.boardDelete(no);
			} else {
				mapper.boardSubjectUpdate(no); // 관리자가 삭제한 게시글입니다.
			}
			mapper.boardDepthDecrement(vo.getRoot()); // depth감소
		} else {
			bCheck=false;
		}
		return bCheck;
	}
	
	// find
	public List<boardVO> boardFindData(Map map){
		
		return mapper.boardFindData(map);
	}
}
