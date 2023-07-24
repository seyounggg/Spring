package com.sist.main4;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sawon {
	private int sabun;
	private String name;
	private String dept;
	private String job;
	private int pay;
	public Sawon() {}
	public void print() {
		System.out.println("사번:"+sabun);
		System.out.println("이름:"+name);
		System.out.println("부서:"+dept);
		System.out.println("직위:"+job);
		System.out.println("연봉:"+pay);
	}
	public void init() {
		System.out.println("============ 사원정보 ============");
	}
	public void destroy() {
		System.out.println("객체 메모리 해제...");
	}
}
