package com.sist.dao;
import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;
public class StudentDAO extends SqlSessionDaoSupport{
	// list
	public List<StudentVO> studentListData(){
		return getSqlSession().selectList("studentListData");
	}
	// detail
	public StudentVO studentDetailData(int hakbun) {
		return getSqlSession().selectOne("studentDetailData",hakbun);
	}
	// find
	public List<StudentVO> studentFindData(String name) {
		return getSqlSession().selectList("studentFindData",name);
	}
	// insert
	public void studentInsert(StudentVO vo) {
		getSqlSession().insert("studentInsert",vo);
	}
	// update
	public void studentUpdate(StudentVO vo) {
		getSqlSession().update("studentUpdate",vo);
	}
	// delete
	public void studentDelete(int hakbun) {
		getSqlSession().delete("studentDelete",hakbun);
	}
}
