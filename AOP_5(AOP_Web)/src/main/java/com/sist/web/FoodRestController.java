package com.sist.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
/*
	Annotation => 메모리 할당
	provided @Component, @Repository, @Service, @Controller
	@RestController, @ControllerAdvice, and @Configuration 
 */
// 다른 언어 연결 => VueJS
// FoodVO List<FoodVO> 이렇게 보내면 js에서 못받아
// FoodVO => {} / List<FoodVO> => [{},{},{}...] => 무조건 JSON으로 변경 후 전송
@RestController
@CrossOrigin("http://localhost:3000") // port번호 3000번을 우리 서버에서 허용하겠다는 의미!
public class FoodRestController {

}
