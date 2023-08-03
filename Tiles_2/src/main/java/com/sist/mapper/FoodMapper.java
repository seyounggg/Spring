package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
import com.sist.vo.SeoulVO;

public interface FoodMapper {
	@Select("SELECT cno,title,poster FROM food_category ORDER BY cno ASC")
	public List<CategoryVO> foodCategoryData();
	
	@Select("SELECT title,subject FROM food_category WHERE cno=#{cno}")
	public CategoryVO foodCategoryInfoData(int cno);
	
	@Select("SELECT fno,cno,name,poster,phone,type,score,address FROM food_house WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
}
