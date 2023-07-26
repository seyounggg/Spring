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
// 환경설정 파일임을 알려주는 어노테이션
@ComponentScan(basePackages = "com.sist.*")
//<context:component-scan base-package="com.sist.*"/>
@MapperScan(basePackages = "com.sist.mapper")
// <mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
public class EmpConfig {
/* app.xml 파일에 작성했던 코드들 
	<!-- DataBase Setting -->
	<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
		p:driverClassName="oracle.jdbc.driver.OracleDriver"
		p:url="jdbc:oracle:thin:@211.238.142.121:1521:xe"
		p:username="hr"
		p:password="happy"
	/>
	<!-- MyBatis -->
	<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
	/>
 */
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
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}
}
