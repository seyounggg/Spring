package com.sist.main3;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student {
	private int hakbun;
	private String name;
	private int kor,eng,math;
	
	public void print() {
		System.out.println("학번 : "+hakbun);
		System.out.println("이름 : "+name);
		System.out.println("국어점수 : "+kor);
		System.out.println("영어점수 : "+eng);
		System.out.println("수학점수 : "+math);
		System.out.println("총 점 : "+(kor+eng+math));
		System.out.printf("평균:%2f",(kor+eng+math)/3.0);
	}
}
