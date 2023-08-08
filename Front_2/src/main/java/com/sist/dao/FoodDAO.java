package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	// 목록 출력
	public List<FoodLocationVO> foodListData(Map map){
		return mapper.foodListData(map);
	}
	
	// totalpage
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	
	// 상세보기
	public FoodLocationVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
	
	// 검색
	public List<FoodLocationVO> foodFindData(Map map){
		return mapper.foodFindData(map);
	}
	
	// 검색 총페이지
	public int foodFindTotalPage(String address) {
		return mapper.foodFindTotalPage(address);
	}
}
