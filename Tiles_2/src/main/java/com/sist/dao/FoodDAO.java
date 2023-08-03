package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	//@Select("SELECT cno,title,poster FROM food_category ORDER BY cno ASC")
	public List<CategoryVO> foodCategoryData(){
		return mapper.foodCategoryData();
	}
	
	public CategoryVO foodCategoryInfoData(int cno) {
		return mapper.foodCategoryInfoData(cno);
	}
	
	//@Select("SELECT fno,cno,name,poster,phone,type,score FROM food_house WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno){
		return mapper.foodListData(cno);
	}
}
