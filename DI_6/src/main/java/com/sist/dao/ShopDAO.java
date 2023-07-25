package com.sist.dao;

import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sist.vo.SeoulShopVO;

public class ShopDAO extends SqlSessionDaoSupport{
	public List<SeoulShopVO> shopListData(){
		return getSqlSession().selectList("shopListData");
	}
	public SeoulShopVO shopDetailData(int no) {
		return getSqlSession().selectOne("shopDetailData",no);
	}
}
