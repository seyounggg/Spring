package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	public List<DataBoardVO> databoardListData(Map map){
		return mapper.databoardListData(map);
	}
	
	public int databoardTotalPage() {
		return mapper.databoardTotalPage();
	}
	
	public void databoardInsert(DataBoardVO vo) {
		mapper.databoardInsert(vo);
	}
	
	// 상세보기
//	@Update("UPDATE springDataBoard SET "
//			+ "hit=hit+1 "
//			+ "WHERE no=#{no}")
//	public void hitIncrement(int no);
//	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'yyyy-MM-dd') as dbday,"
//			+ "filename,filesize,filecount "
//			+ "FROM springDataBoard "
//			+ "WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
	
	// 검색하기
//	@Select("SELECT no,subject,name,TO_CHAR(regdate,'yyyy-MM-dd') as dbday,hit "
//			+ "FROM springDataBoard WHERE ${fs} LIKE '%'||#{ss}||'%'")
	public List<DataBoardVO> databoardFindData(Map map){
		return mapper.databoardFindData(map);
	}
	
	// update-data
	public DataBoardVO databoardUpdateData(int no) {
		return mapper.databoardDetailData(no);
	}
	
	// update
	public boolean databoardUpdate(DataBoardVO vo) {
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck=true;
			mapper.databoardUpdate(vo);
		}
		return bCheck;
	}
	
	// delete
	public boolean databoardDelete(int no,String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
			mapper.databoardDelete(no);
		}
		return bCheck;
	}
}
