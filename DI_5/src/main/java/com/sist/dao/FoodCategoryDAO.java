package com.sist.dao;
import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FoodCategoryDAO extends SqlSessionDaoSupport{
	
	public List<FoodCategoryVO> foodCategoryData(){
		return getSqlSession().selectList("foodCategoryData");
	}
	
}
