package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Setter;
public class MainClass {
	@Setter
	private GoodsDAO dao; // app.xml에서 값을 채워줌
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		// MainClass mc=new MainClass(); 이렇게 코딩하면 안돼! null!! 비어있음
		// 이유 : spring에서 값을 주입했기 떄문에 값은 Spring에 있음
		MainClass mc=(MainClass)app.getBean("mc");
		List<String> list=mc.dao.goodsNameList();
		for(String name:list) {
			System.out.println("name:"+name);
		}
	}
}
