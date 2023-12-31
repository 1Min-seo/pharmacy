package com.ms.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.MemberVO;

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
	@RequestMapping("/joinRequestParam")
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
	
	//데이터 객체(DTO/VO)사용
	@RequestMapping("/joinDataObject")
	public String join(MemberVO vo, Model model) {
		model.addAttribute("vo",vo);
		model.addAttribute("method","데이터 객체");
		
		return "member/info";
	}
	
	//@PathVariable로 form의 데이터를 접근
	@RequestMapping("/joinPathVariable/{name}/{gender}/{email}")
	public String join(@PathVariable String name, Model model,
			@PathVariable String gender, @PathVariable String email) {
		//@PathVariable은 파라미터마다 어노테이션을 따로 지정해주어야한다.
		//Model의 위치는 파라미터 처음, 중간, 끝, 어디여도 상관없다
		model.addAttribute("name",name);
		model.addAttribute("gender",gender);
		model.addAttribute("email",email);
		model.addAttribute("method","@PathVariable");
		
		return "member/info";
	}
	
	//1. forward - 요청하는 url과 응답하는 페이지가 서로 다르게 지정할 수 있는 형태
	//			 - 실제 응답하는 페이지의 url이 아닌, 요청하는 url이 유지되는 형태
	//2. redirect- 요청하는 url에 해당하는 페이지가 응답하는 형태
	
	//로그인 화면
	@RequestMapping("/login")
	public String login() {
		return "member/login";
	}
	
	//로그인 결과 화면
	@RequestMapping("/login_result")
	public String login_result(String id, String pw) {
		//아이디, 비번 일치 -> home 화면으로
		//일치하지 않으면 로그인 화면으로
		//DB에서 읽어온 데이터가 hong, 1234라고 가정
		if(id.equals("hong") && pw.equals("1234")){
			return "redirect:/";
		}else {
			return "redirect:login";
		}
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(HttpSession session, Locale locale, Model model) {

		session.removeAttribute("category");
		
		return "home";
	}
}
