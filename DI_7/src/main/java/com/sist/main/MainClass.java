package com.sist.main;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.mapper.*;
// @Component = <bean id="mainClass" class="com.sist.main.MainClass"> 와 같다
// id가 없는 경우에는 class명이 아이디로 설정된다(펏자만 소문자)
// EmpDAO => empDAO
@Component("mc")
public class MainClass {
	@Autowired
	private EmpDAO dao;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mc");
		List<EmpVO> list=mc.dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getDbday()+" "
					+vo.getDbsal());
		}
		
		System.out.println("======================================");
		Scanner scan=new Scanner(System.in);
		System.out.print("사원번호 입력:");
		int empno=scan.nextInt();
		EmpVO vo=mc.dao.empDeatilData(empno);
		System.out.println("사원번호:"+vo.getEmpno());
		System.out.println("이름:"+vo.getEname());
		System.out.println("직위:"+vo.getJob());
		System.out.println("입사일:"+vo.getDbday());
		System.out.println("연봉:"+vo.getDbsal());
		System.out.println("성과급:"+vo.getComm());
		
		System.out.println("===================================");
		System.out.print("검색어:");
		String ename=scan.next();
		list=mc.dao.empFindData(ename);
		for(EmpVO evo:list) {
			System.out.println(evo.getEmpno()+" "
					+evo.getEname()+" "
					+evo.getJob()+" "
					+evo.getDbday()+" "
					+evo.getDbsal());
		}
	}

}
