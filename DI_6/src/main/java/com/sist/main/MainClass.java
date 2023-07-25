package com.sist.main;

import java.util.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
import com.sist.vo.*;
public class MainClass {

	public static void main(String[] args) {
		
	}
//	@Test
//	public void locationMain() {
//		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
//		LocationDAO ldao=(LocationDAO)app.getBean("ldao");
//		List<SeoulLocationVO> list=ldao.locationListData();
//		for(SeoulLocationVO vo:list) {
//			System.out.println(vo.getNo()+"."+vo.getTitle());
//		}
//	}
//	
//	@Test
//	public void locationDetail() {
//		Scanner scan=new Scanner(System.in);
//		System.out.print("명소번호:");
//		int no=scan.nextInt();
//		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
//		LocationDAO dao=(LocationDAO)app.getBean("ldao");
//		SeoulLocationVO vo=dao.locationDetailData(no);
//		System.out.println("title:"+vo.getTitle());
//		System.out.println("address:"+vo.getAddress());
//		System.out.println("message:"+vo.getMsg());
//	}
	
//	@Test
//	public void shopListData() {
//		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
//		ShopDAO dao=(ShopDAO)app.getBean("sdao");
//		List<SeoulShopVO> slist=dao.shopListData();
//		for(SeoulShopVO vo:slist) {
//			System.out.println(vo.getNo()+"."+vo.getTitle());
//		}
//	}
//	
//	@Test
//	public void shopDetailData() {
//		Scanner scan=new Scanner(System.in);
//		System.out.println("shop번호:");
//		int no=scan.nextInt();
//		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
//		ShopDAO dao=(ShopDAO)app.getBean("sdao");
//		SeoulShopVO vo=dao.shopDetailData(no);
//		System.out.println("title:"+vo.getTitle());
//		System.out.println("address:"+vo.getAddress());
//		System.out.println("msg:"+vo.getMsg());
//	}
	
	@Test
	public void natureListData() {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		NatureDAO dao=(NatureDAO)app.getBean("ndao");
		List<SeoulNatureVO> list=dao.natureListData();
		for(SeoulNatureVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getTitle());
		}
	}
	
	@Test
	public void natureDetailData() {
		Scanner scan=new Scanner(System.in);
		System.out.print("자연번호입력:");
		int no=scan.nextInt();
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		NatureDAO dao=(NatureDAO)app.getBean("ndao");
		SeoulNatureVO vo=dao.natureDetailData(no);
		System.out.println("title:"+vo.getTitle());
		System.out.println("address:"+vo.getAddress());
		System.out.println("msg:"+vo.getMsg());
	}
}
