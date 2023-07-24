package com.sist.main2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
	객체 생성, init-method
	setter 처리
	destroy-method
 */
public class MainClass {
	public static void main(String[] args) {
//		String[] xml= {"member.xml","sawon.xml","student.xml"};
		ApplicationContext app=new ClassPathXmlApplicationContext("app-*.xml");
		// 원래 xml파일명을 배열에 잡은 것처럼 했는데, app-를 앞에 붙여서 수정함
		// 이럴경우 ClassPath.. (_____) <- 이부분처럼 작성하면 됨 
		Sawon s=(Sawon)app.getBean("sa");
		// s.init(); -> 이렇게 호출하기 보다는 spring자체에서 처리!
		// 근데 xml에서 init-method 로 init을 호출해버리면 순서가 맞지 않기때문에 이런 경우에는
		// 각각 호출
		s.init();
		s.print();
		Member m=(Member)app.getBean("mem");
		m.init();
		m.print();
		Student std=app.getBean("std",Student.class);
		std.init();
		std.print();
	}
}
