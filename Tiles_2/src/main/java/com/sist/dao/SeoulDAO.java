package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.SeoulMapper;
import com.sist.vo.SeoulVO;

@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulListData(Map map){
		mapper.seoulListData(map);
		return (List<SeoulVO>)map.get("pResult");
	}
	
	public Integer seoulTotalPage(Map map) {
		mapper.seoulTotalPage(map);
		return (Integer)map.get("pTotal");
	}
	
	public SeoulVO seoulDetailData(Map map) {
		return mapper.seoulDetailData(map);
	}
}
