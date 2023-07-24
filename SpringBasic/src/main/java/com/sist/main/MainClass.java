package com.sist.main;
/*
	MainClass는 Hello클래스에 의존을 한다
	=> 결합성이 강한 프로그램(의존성이 강한 프로그램)
	=> 장점 : 연결하기 쉽다
	=> 단점 : 변경시에 다른 클래스에 영향력이 강하다
	*** 스프링에서는 의존성이 낮은 프로그램을 구현한다
		클래스 수정시 => 다른 클래스에 영향이 없게 만든다
		가급적이면 new를 사용하지 않는다.
		
 */
public class MainClass {
	public static void main(String[] args) {
		Hello hello=new Hello();
		String msg=hello.sayHello2("홍길동");
		System.out.println(msg);
	}
}