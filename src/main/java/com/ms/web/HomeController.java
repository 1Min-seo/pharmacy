package com.ms.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping("/first")
	public String view(Model model) {
		
		model.addAttribute("today",
				new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		
		return "index";
	}
	
	@RequestMapping("/second")
	public ModelAndView view() {
		ModelAndView mav=new ModelAndView();
		mav.addObject("now",
				new SimpleDateFormat("a hh/mm/ss").format(new Date()));
				
		mav.setViewName("index");
		
		return mav;
	}
	
	@RequestMapping("/join")
	public String join() {
		return "member/join";
	}
	
	//HttpServletRequest로 form의 데이터를 접근
	@RequestMapping("/joinRequest")
	public String join(HttpServletRequest request, Model model) {
		//데이터를 전달할 때에는 모델에 담아서 전달하는데
		//전달할 데이터는 request에 담겨있다.
		model.addAttribute("name", request.getParameter("name"));
		model.addAttribute("gender", request.getParameter("gender"));
		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("method", "httpServeltRequest");
		
		
		return "member/info";
	}
	
	//@RequestParam으로 form의 데이터를 접근
	@RequestMapping("/joinRequestPara")
	public String join(Model model, String name,
			@RequestParam("gender") String g, String email) {
		//지정해도 되지만 지정하지 않아도 기본적으로 @RequestParam 처리가 된다.
		//form 태그의 name 속성과 통일을 하면 RequestParam의 매개 변수로 지정을 하지 않아도 되지만,
		//name 속성과 매개 변수의 이름을 다르게 설정하면 Request Param의 매개변수로 지정을 해주어야 한다.
	
		model.addAttribute("name",name);
		model.addAttribute("gender",g);
		model.addAttribute("email",email);
		model.addAttribute("method", "@RequestParam");
		
		return "member/info";
	}
	
}
