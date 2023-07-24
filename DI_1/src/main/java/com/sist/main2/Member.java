package com.sist.main2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Member {
	private String id,name,address,phone,sex;
	
	//개발자가 호출
	public void print() {
		System.out.println("id:"+id);
		System.out.println("Name:"+name);
		System.out.println("Address:"+address);
		System.out.println("Phone:"+phone);
		System.out.println("Sex:"+sex);
	}
}
