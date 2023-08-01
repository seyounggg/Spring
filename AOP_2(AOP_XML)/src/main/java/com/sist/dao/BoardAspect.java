package com.sist.dao;
/*
	언제 어디서 호출할지 여부 확이
	1. 시점 => pointcut => 메소드
	2. 호출위치 => joinpoint
	@Before : try 시작 전에 호출
	@After : finally
	@Around : 위 아래 => 로그파일(시작, 끝 시간에 체크), 트렌젝션 처리
	@After-Throwing : catch(오류 발생)
	@After-Returning : 
	
	@Transactional
	public void display(){
		try{
			@Before
			------------- @Around => conn.setAutoCommit()
			=== 핵심 코딩
			------------- conn.commit()
		}catch(Exception e){
			@After-Throwing conn.rollback()
		}finally {
			@After conn.setAutoCommit(true)
		}
		return ""; @After-Returning
	}
	----------------------------- advice
	
	----------------------------- aspect
	
 */
public class BoardAspect {
	public void before() { // 문장 수행전에 호출
		System.out.println("오라클 연결!!");
	}
	public void after() { // 문장 수행이 종료 => finally
		System.out.println("오라클 해제!!");
	}
}
