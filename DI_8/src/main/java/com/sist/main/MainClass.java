package com.sist.main;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="/Users/seyeong/Desktop/Spring/DI_8/src/main/java/com/sist/main/app.xml";
		ApplicationContext app=new ClassPathMapApplicatonContext(path);
		Sawon sa=(Sawon)app.getBean("sa");
		System.out.println("사번:"+sa.getSabun());
		System.out.println("이름:"+sa.getName());
		System.out.println("부서:"+sa.getDept());
		System.out.println("직위:"+sa.getJob());
	}

}
