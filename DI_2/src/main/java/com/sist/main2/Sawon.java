package com.sist.main2;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//import lombok.Getter;
//import lombok.Setter;


/*
	1. 메모리 할당 => 생성자 호출
	2. setter DI => sexXxx() 값을 채운다
			=> property에 값을 채우지 않으면 안됨
	3. init-method에 등록된 method 호출
	4. 개발자에 의해 필요한 메소드 호출
	5. destroy-method 메모리 해제
*/
public class Sawon implements BeanNameAware,InitializingBean,DisposableBean{
	private int sabun;
	private String name,dept;
	// <bean id="sa" class=""
	public Sawon() {
		System.out.println("Sawon() Call...");
	}
	public int getSabun() {
		return sabun;
	}

	public void setSabun(int sabun) {
		System.out.println("setSabun() Call...:"+sabun);
		this.sabun = sabun;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("setName() Call...:"+name);
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		System.out.println("setDept() Call...:"+dept);
		this.dept = dept;
	}

	public void init() {
		System.out.println("init() Call...");
		System.out.println("====== 사원정보 ======");
	}
	
	public void print() {
		System.out.println("사용자 정의 호출 ...");
		System.out.println("sabun:"+sabun);
		System.out.println("name:"+name);
		System.out.println("dept:"+dept);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Setter 처리 종료");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("setBeanName():"+name); // bean Name을 읽어온다
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("객체 소멸");
		
	}
	
}
