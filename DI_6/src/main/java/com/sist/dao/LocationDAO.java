package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.sist.vo.*;
import java.util.*;
public class LocationDAO extends SqlSessionDaoSupport{

	public List<SeoulLocationVO> locationListData(){
		return getSqlSession().selectList("locationListData");
	}
	
	public SeoulLocationVO locationDetailData(int no) {
		return getSqlSession().selectOne("locationDetailData",no);
	}
}
