package com.sist.main;

import java.util.*;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class EmpVO {
	private int empno,mgr,sal,deptno,comm;
	private String ename,job;
	private Date hiredate;
}
