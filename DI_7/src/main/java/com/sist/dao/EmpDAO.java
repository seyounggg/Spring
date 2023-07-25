package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;
@Repository("dao")
public class EmpDAO {
	@Autowired
	private EmpMapper mapper;
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
	
	public EmpVO empDeatilData(int empno) {
		return mapper.empDeatilData(empno);
	}
	
	public List<EmpVO> empFindData(String ename){
		return mapper.empFindData(ename);
	}
}
