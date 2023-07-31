package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;

@Repository
public class EmpDAO extends SqlSessionDaoSupport{
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	//				    ----------------------------	xml때문에
	public List<String> empGetNameData(){
		return getSqlSession().selectList("empGetNameData");
	}
	public List<EmpVO> empInfoData(Map map){
		return getSqlSession().selectList("empInfoData",map);
	}

}
