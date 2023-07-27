package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//<context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages = "com.sist.*")
//<mybatis-spring:scan base-package="com.sist.mapper2"/>
@MapperScan(basePackages = "com.sist.mapper2")
public class EmpConfig {
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@211.238.142.121:1521:xe");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
	
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}
}
