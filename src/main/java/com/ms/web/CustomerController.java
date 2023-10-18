package com.ms.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {
	
	//고객 관리 목록
	@RequestMapping("/list.cu")
	public String list(HttpSession session) {
		//서블릿에서 request에서 getsession으로 세션을 가져왔다면,
		//스프링에서는 바로 세션에 접근할 수 있게 HttpSession을 지원한다.
		
		//category attribute의 값에 따라 active 속성을 결정한다.
		session.setAttribute("category", "cu");
		
		return "customer/list";
	}
	
	
}
