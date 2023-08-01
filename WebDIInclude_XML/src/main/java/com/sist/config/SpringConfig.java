package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
/*
	요청 : .do
		DispatcherServlet
		=> HandlerAdapter : Model클래스 찾기
		=> HandlerMapping : @GetMapping, @PostMapping,@RequestMapping
						=> 	메소드 찾기 => 호출
		
		DispatcherServlet
		=> request => ViewResolver
							|
						JSP로 값 전송
 */

// 스프링에서 클래스 등록
@Configuration
// <context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages = {"com.sist.*"})
// <mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
@MapperScan(basePackages = {"com.sist.mapper"})
public class SpringConfig implements WebMvcConfigurer{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable(); // enable() 생성할 때 쓰는 메소드
	}
	
	/*
	<servlet>
		<servlet-name>dispatcher</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/config/application-*.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
	HandlerMapping / HandlerAdapter
 */

	/*
	 	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		p:prefix="/"
		p:suffix=".jsp"
		/>
	 */
	@Bean("viewResolver")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver iv=new InternalResourceViewResolver();
		iv.setPrefix("/");
		iv.setSuffix(".jsp");
		return iv;
	}
	/*
	 <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
	 */
	@Bean("multipartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver cr=new CommonsMultipartResolver();
		return cr;
	}
	/*
		<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
		p:driverClassName="#{db['driver']}"
		p:url="#{db['url']}"
		p:username="#{db['username']}"
		p:password="#{db['password']}"
		p:maxActive="#{db['maxActive']}"
		p:maxIdle="#{db['maxIdle']}"
		p:maxWait="#{db['maxWait']}"
		/>
	 */
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@211.238.142.121:1521:xe");
		ds.setUsername("hr");
		ds.setPassword("happy");
		ds.setMaxActive(10);
		ds.setMaxIdle(10);
		ds.setMaxWait(-1);
		return ds;
	}
	
	/*
	 * <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
		/>
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}
}
