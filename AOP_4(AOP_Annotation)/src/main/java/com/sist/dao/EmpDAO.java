package com.sist.dao;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository
public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.121:1521:XE";
	
	public EmpDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {}
	}
	
	public void disConncetion() {
		try {
			if(conn!=null) conn.close();
			if(ps!=null) ps.close();
		} catch (Exception e) {}
	}
	
	public List<EmpVO> empListData(){
		List<EmpVO> list=new ArrayList<EmpVO>();
		try {
			//getConnection(); - spring을 통해 자동 호출되게 만드려는 부분
			String sql="SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD'),sal "
					+ "FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setSal(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			//e.printStackTrace(); - spring을 통해 자동 호출되게 만드려는 부분
		} finally {
			//disConncetion(); - spring을 통해 자동 호출되게 만드려는 부분
		}
		return list;
	}
}
