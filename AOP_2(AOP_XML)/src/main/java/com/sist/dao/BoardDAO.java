package com.sist.dao;

public class BoardDAO {
	public String find(String name) {
		return name;
	}
	public void aopSelect(String name) {
		System.out.println(name+":Select 문장 수행"); // 핵심모듈
	}
	public void aopInsert() {
		System.out.println("Insert 문장 수행");
	}
	public void aopUpdate() {
		System.out.println("Update 문장 수행");
	}
	public void aopDelete() {
		System.out.println("Delete 문장 수행");
	}
}
