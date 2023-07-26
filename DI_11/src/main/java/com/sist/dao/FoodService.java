package com.sist.dao;
// dao 와 service 의 차이!!
// 단독 / 통합

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
	@Autowired
	private FoodDAO fdao;
	@Autowired
	private CategoryDAO cdao;
	
	public List<CategoryVO> categoryListData(){
		return cdao.categoryListData();
	}
	public List<FoodVO> foodCategoryListData(int cno){
		return fdao.foodCategoryListData(cno);
	}
	public FoodVO foodDetailData(int fno) {
		return fdao.foodDetailData(fno);
	}
}
