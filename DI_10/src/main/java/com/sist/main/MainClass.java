package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;

@Component("mc")
public class MainClass {
	@Autowired //spring에서 자동으로 객체 주소값을 찾아서 주입
	@Qualifier("fdao") // 여러개가 충돌되면 선택해야하는데 그럴 때 @Qualifier 로 해당 객체를 지정한다.
	private Board board;
	@Autowired
	private FreeBoardDAO f;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
	}
}
