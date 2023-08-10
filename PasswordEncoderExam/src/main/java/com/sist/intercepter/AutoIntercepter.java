package com.sist.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
									인터셉트(preHandle)		  인터셉트(posterHanler)	인터셉트(afterCompletion)
	요청(.do) => DispatcherServlet ==========> HandlerMapping =========> ViewResolver ===============> JSP
										@Controller / @RestController	- return값을 받아서 화면 변경
										=> @GetMapping, @PostMapping
	ex)자동로그인
	요청을 보내면 HandlerMapping이 login.do를 찾으러 가는데 HandlerMapping이 login 찾으러 가기 전에
	Intercepter로 자동 로그인 처리를 할 수 있다! => 미리 로그인 처리 할 때 사용하는 건 "preHandler"
	
	로그인 처리가 되고 return 값을 받아서 화면을 이동하기 전에 처리해야할 일이 있으면 posterHandler를 이용
	
	jsp 출력 전 예를들어 권한처리를 하려면 afterCompletion 이용
	
	
	application-context파일에 bean에 등록하므로 메모리 할당이 됨
	<bean id="autoInterceptor" class="com.sist.intercepter.AutoIntercepter"/>
	
	그래서 따로 메모리 할당을 하지 않아도 됨.
 */
public class AutoIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle Call...");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle Call...");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion Call...");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
	
}
