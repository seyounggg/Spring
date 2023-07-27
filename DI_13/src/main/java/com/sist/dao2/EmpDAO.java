package com.sist.dao2;
import com.sist.vo.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper2.*;
@Repository
public class EmpDAO {
	@Autowired
	private EmpDeptMapper mapper;
	
	public List<EmpVO> empdeptListData(){
		return mapper.empdeptListData();
	}
	
	public EmpVO empdeptDetailData(int empno) {
		return mapper.empdeptDetailData(empno);
	}
}
