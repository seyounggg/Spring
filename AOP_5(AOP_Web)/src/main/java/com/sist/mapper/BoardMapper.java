package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface BoardMapper {
	// list
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,group_tab,num "
			+ "FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
			+ "FROM (SELECT no,subject,name,regdate,hit,group_tab "
			+ "FROM springReplyBoard ORDER BY group_id DESC, group_step ASC)) " // 이중정렬
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<boardVO> boardListData(Map map);
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springReplyBoard")
	public int boardTotalPage();
	
	// insert
	@Insert("INSERT INTO springReplyBoard(no,name,subject,content,pwd,group_id) "
			+ "VALUES(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
			+ "(SELECT NVL(MAX(group_id)+1,1) FROM springReplyBoard))") //서브쿼리
	public void boardInsert(boardVO vo);
	
	// detail
	@Update("UPDATE springReplyBoard SET hit=hit+1 WHERE no=#{no}")
	public void hitIncrement(int no);
	@Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit "
			+ "FROM springReplyBoard WHERE no=#{no}")
	public boardVO boardDetailData(int no);
	
	// reply -> transaction(spring : AOP)
	@Select("SELECT group_id,group_step,group_tab "
			+ "FROM springReplyBoard "
			+ "WHERE no=#{no}")
	public boardVO boardParentInfoData(int no);
	@Update("UPDATE springReplyBoard SET "
			+ "group_step=group_step+1 "
			+ "WHERE group_id=#{group_id} AND group_step>#{group_step}")
	public void boardGroupStepIncrement(boardVO vo);
	@Insert("INSERT INTO springReplyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) "
			+ "VALUES(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},#{group_id},#{group_step},#{group_tab},#{root})")
	public void boardReplyInsert(boardVO vo);
	@Update("UPDATE springReplyBoard SET "
			+ "depth=depth+1 "
			+ "WHERE no=#{no}")
	public void boardDepthIncrement(int no);
	
	// update
	
	// delete -> transaction(spring : AOP)
	
	// 다중검색(동적쿼리)
}