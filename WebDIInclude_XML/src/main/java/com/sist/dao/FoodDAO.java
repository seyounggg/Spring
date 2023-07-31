package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class FoodDAO {
	@Autowired // Spring으로 부터 구현된 클래스의 주소값을 받는다(자동주입)
	private FoodMapper mapper;
	
	public List<CategoryVO> foodCategoryData(Map map){
		return mapper.foodCategoryData(map);
	}
	
	public CategoryVO foodCategoryInfoData(int cno) {
		return mapper.foodCategoryInfoData(cno);
	}
	
	public List<FoodVO> foodListData(int cno){
		return mapper.foodListData(cno);
	}
	
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
}
