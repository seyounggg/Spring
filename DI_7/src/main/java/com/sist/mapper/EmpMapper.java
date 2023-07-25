package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.EmpVO;

public interface EmpMapper {
	@Select("SELECT empno,ename,job,"
			+ "TO_CHAR(hiredate,'yyyy-MM-dd') as dbday,TO_CHAR(sal,'$999,999') as dbsal,deptno,comm "
			+ "FROM emp")
	public List<EmpVO> empListData();
	
	@Select("SELECT empno,ename,job,mgr,TO_CHAR(hiredate,'yyyy-MM-dd') as dbday,"
			+ "TO_CHAR(sal,'$999,999') as dbsal,NVL(comm,0) as comm,deptno "
			+ "FROM emp "
			+ "WHERE empno=#{empno}")
	public EmpVO empDeatilData(int empno);
	
	@Select("SELECT empno,ename,job,mgr,TO_CHAR(hiredate,'yyyy-MM-dd') as dbday,"
			+ "TO_CHAR(sal,'$999,999') as dbsal,NVL(comm,0) as comm,deptno "
			+ "FROM emp "
			+ "WHERE ename LIKE '%'||#{ename}||'%'")
	public List<EmpVO> empFindData(String ename);
}
