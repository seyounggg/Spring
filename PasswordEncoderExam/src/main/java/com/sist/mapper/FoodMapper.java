package com.sist.mapper;
import java.util.*;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
public interface FoodMapper {
	// XML과 매칭할떄!!(동적쿼리 ,복잡한 쿼리문을 사용하면 xml을 사용하는게 더 편리하다)
	// 1. id 와 메소드명이 동일해야한다.
	// 2. resultType 과 return형이 동일해야한다.
	// 3. parameterType 과 매개변수가 동일해야한다.
	
	// <select id="foodCategoryListData" resultType="CategoryVO" parameterType="hashMap">
	public List<CategoryVO> foodCategoryListData(Map map);
	
	//<select id="foodCategoryInfoData" resultType="CategoryVO" parameterType="int">
	public CategoryVO foodCategoryInfoData(int cno);
	
	//<select id="foodListData" resultType="foodVO" parameterType="int">
	public List<FoodVO> foodListData(int cno);
	
	//<select id="foodDetailData" resultType="FoodVO" parameterType="int">
	public FoodVO foodDetailData(int fno);
}
