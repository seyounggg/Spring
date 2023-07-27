package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface BoardMapper {
	// 목록 출력 => paging
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'yyyy-MM-dd') as dbday,hit,num "
			+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(springBoard sb_no_pk)*/no,subject,name,regdate,hit "
			+ "FROM springBoard)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	//							map.get("start") map.get("end")
	public List<BoardVO> boardListData(Map map);
	
	// totalpage
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springBoard")
	public int boardTotalPage();
	
	@Select("INSERT INTO springBoard(no,name,subject,content,pwd) "
			+ "VALUES(sb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
	//	#{} => get();				vo.getName(),vo.getSubject(),vo.getContent(),vo.getPwd()
	public void boardInsert(BoardVO vo);
	
	//detail
	@Update("UPDATE springBoard SET hit=hit+1"
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'yyyy-MM-dd') as dbday,hit FROM springBoard WHERE no=#{vo}")
	public BoardVO boardDetailData(int no);
}
