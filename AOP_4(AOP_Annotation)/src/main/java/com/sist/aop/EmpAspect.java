package com.sist.aop;
import java.util.*;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
@Aspect // 공통 모듈임을 표시하기 위함
@Component // 메모리 할당 담당
public class EmpAspect {
	@Autowired
	private EmpDAO dao;
	
	@Before("execution(* com.sist.dao.EmpDAO.emp*(..))") // try 시작과 동시에 처리해라
	public void getConnection() {
		System.out.println("오라클 연결");
		dao.getConnection();
	}
	
	@After("execution(* com.sist.dao.EmpDAO.emp*(..))") // finally에 서 호출해라
	public void disConnectiorn() {
		System.out.println("오라클 해제");
		dao.disConncetion();
	}
	
	@AfterReturning(value="execution(* com.sist.dao.EmpDAO.emp*(..))",returning = "obj") // : 정장수행 => return 값을 받아서 수행
	public void print(Object obj) {
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getDbday()+" "
					+vo.getSal());
		}
	}
	@AfterThrowing(value="execution(* com.sist.dao.EmpDAO.emp*(..))",throwing = "e") // : catch 절 수행(에러처리)
	public void exception(Throwable e) {
		System.out.println("에러발생...");
		e.printStackTrace();
	}
	
}
