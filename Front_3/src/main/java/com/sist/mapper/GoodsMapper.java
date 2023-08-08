package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.GoodsVO;
public interface GoodsMapper {
	// 인라인뷰 => Top-N (포트폴리오 -> 인라인뷰를 이용한 페이징 기법 사용)
	@Select("SELECT no,goods_name as name,goods_poster as poster,goods_price as price,num "
			+ "FROM (SELECT no,goods_name,goods_poster,goods_price,rownum as num "
			+ "FROM (SELECT no,goods_name,goods_poster,goods_price "
			+ "FROM goods_all ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(Map map);
	
	// totalpage
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsTotalPage();
	
	// detail => 장바구니(session 사용)
	@Update("UPDATE goods_all SET hit=hit+1 WHERE no=#{no}")
	public void hitImplement(int no);
	@Select("SELECT no,goods_name as name,goods_sub as sub,goods_price as price,goods_discount as discount,"
			+ "goods_first_price as first_price,goods_delivery as delivery,goods_poster as poster,hit,account "
			+ "FROM goods_all "
			+ "WHERE no=#{no}")
	public GoodsVO goodsDetailData(int no);
	
}
