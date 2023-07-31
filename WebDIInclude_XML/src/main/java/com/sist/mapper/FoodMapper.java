package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import com.sist.vo.*;

public interface FoodMapper {
	// 동적 쿼리를 이용한 경우 <script></script> 사용!
	// Q. @Select가 어디에 적용되길래 <script> 태그를 사용하는지? => 자바에서 처리하는거 아닌가.
	@Select("<script>"
			+ "SELECT cno,poster,title "
			+ "FROM food_category "
			+ "WHERE "
			+ "<if test=\"cno==1\">"
			+ "cno BETWEEN 1 AND 12 "
			+ "</if>"
			+ "<if test=\"cno==2\">"
			+ "cno BETWEEN 13 AND 18 "
			+ "</if>"
			+ "<if test=\"cno==3\">"
			+ "cno BETWEEN 19 AND 30 "
			+ "</if>"
			+ "ORDER BY cno ASC"
			+ "</script>")
	public List<CategoryVO> foodCategoryData(Map map);
	
	// 카테고리 정보 읽기
	@Select("SELECT title,subject "
			+ "FROM food_category "
			+ "WHERE cno=#{cno}")
	public CategoryVO foodCategoryInfoData(int cno);
	
	// 카테고리별 맛집 정보 읽기
	@Select("SELECT fno,cno,name,poster,address,score,type,phone "
			+ "FROM food_house "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	
	// 맛집별 상세보기
	// mapper에서의 매개변수는 1개만 가능
	@Select("SELECT fno,cno,name,score,poster,phone,address,type,time,parking,price,menu "
			+ "FROM food_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
}
