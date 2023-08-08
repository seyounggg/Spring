package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.GoodsMapper;
@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsVO> goodsListData(Map map){
		return mapper.goodsListData(map);
	}
	
	public int goodsTotalPage() {
		return mapper.goodsTotalPage();
	}
	
	public GoodsVO goodsDetailData(int no){
		mapper.hitImplement(no);
		return mapper.goodsDetailData(no);
	}
}
