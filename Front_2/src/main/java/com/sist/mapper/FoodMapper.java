package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodLocationVO;
public interface FoodMapper {
	// 목록 출력
	@Select("SELECT fno,name,poster,score,num "
			+ "FROM (SELECT fno,name,poster,score,rownum as num "
			+ "FROM (SELECT fno,name,poster,score "
			+ "FROM food_location ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodLocationVO> foodListData(Map map);
	
	// 총페이지
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_location")
	public int foodTotalPage();
	
	// 상세보기
	@Select("SELECT fno,name,poster,score,parking,time,type,address,phone,price,menu "
			+ "FROM food_location "
			+ "WHERE fno=#{fno}")
	public FoodLocationVO foodDetailData(int fno);
	
	// 검색 | 검색, 실시간 => Vue/React
	@Select("SELECT fno,name,poster,score,num "
			+ "FROM (SELECT fno,name,poster,score,rownum as num "
			+ "FROM (SELECT fno,name,poster,score "
			+ "FROM food_location ORDER BY fno ASC WHERE address LIKE '%'||#{address}||'%')) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodLocationVO> foodFindData(Map map);
}
