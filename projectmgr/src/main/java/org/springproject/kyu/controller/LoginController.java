package org.springproject.kyu.controller;


import javax.management.AttributeValueExp;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springproject.kyu.dto.VisiterDto;
import org.springproject.kyu.service.VisiterService;



@Controller
public class LoginController {
	
	@Autowired
	VisiterService visiterService;
	
	
	@GetMapping(path="/login")
	public String showPage() {
		return "login";
	}
	
	@GetMapping(path="/logout")
	public String logout(HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap modelMap) {
		
		session.invalidate();

		req.setAttribute("resultMsg", "다음에 또 방문해주세요~");
		return "redirect:main";
	}
	
	
	@PostMapping(path="/logincheck")
	public String loginCheck(@RequestParam(name="email") String email ,
			@RequestParam(name="password") String password,
			HttpSession session,
			RedirectAttributes redirectAttr,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelAndView modelAndView) throws Exception{
		
		String clientIp = (String)req.getAttribute("clientIp");
		VisiterDto visiter = visiterService.checkLogin(email, password, clientIp);
		String viewName = "";
		
		
		if( visiter != null ) {
			session.setAttribute("email", email);
			session.setMaxInactiveInterval(10*60); // 10분 유지
			 
			Cookie info = new Cookie("email", email);    // 쿠키를 생성
			info.setMaxAge(60*60*10);                                 // 쿠키의 유효기간
			info.setPath("/");
			res.addCookie(info); 
			
			req.setAttribute("resultMsg", visiter.getName()+"님 반갑습니다!");
			viewName = "main";

		}else {
			
			redirectAttr.addFlashAttribute("errorMessage", "이메일이 없거나 비밀번호가 잘못 됬습니다.");
			viewName = "redirect:login";
			
		}
		
		
		return viewName;
	}
	

}
