package com.sist.main;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getSal()+" "
					+vo.getHiredate());
		}
		System.out.println("============================");
		Scanner scan=new Scanner(System.in);
		System.out.print("사번입력 :");
		int empno=scan.nextInt();
		EmpVO vo=dao.empDetailData(empno);
		System.out.println("======= 사원정보 =======");
		System.out.println("사번:"+vo.getEmpno());
		System.out.println("이름:"+vo.getEname());
		System.out.println("직위:"+vo.getJob());
		System.out.println("사수명:"+vo.getMgr());
		System.out.println("입사일:"+vo.getDbday());
		System.out.println("급여:"+vo.getSal());
		System.out.println("성과급:"+vo.getComm());
		System.out.println("부서:"+vo.getDeptno());
	}

}
