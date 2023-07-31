package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;

	public List<SeoulVO> seoulLocationListData(Map map){
		return mapper.seoulLocationListData(map);
	}
	
	public int seoulLocationTotalPage() {
		return mapper.seoulLocationTotalPage();
	}
	
	public List<SeoulVO> seoulNatureListData(Map map){
		return mapper.seoulNatureListData(map);
	}
	
	public int seoulNatureTotalPage() {
		return mapper.seoulNatureTotalPage();
	}
	
	public List<SeoulVO> seoulShopListData(Map map){
		return mapper.seoulShopListData(map);
	}
	
	public int seoulShopTotalPage() {
		return mapper.seoulShopTotalPage();
	}
}
