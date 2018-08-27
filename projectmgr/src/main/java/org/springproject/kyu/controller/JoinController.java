package org.springproject.kyu.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springproject.kyu.dto.VisiterDto;
import org.springproject.kyu.service.VisiterService;

@Controller
public class JoinController {

	@Autowired
	VisiterService visiterService;
	
	@GetMapping(path="/join")
	public String showPage() {
		return "join";
	}
	
	@PostMapping(path="/addvisiter")
	public String addVisiter( @ModelAttribute VisiterDto visiter,
			HttpServletRequest req,
			ModelMap modelMap){

		String clientIp = (String)req.getAttribute("clientIp");

		int result = visiterService.addVisiter(visiter,clientIp);
		String viewName = "";
		String Message = "";
			
			switch(result){
				case VisiterService.SUCCESS:
					Message = "성공적으로 가입되었습니다!";
					viewName = "main";
					break;
				case VisiterService.FAILED:
					Message = "알수 없는 오류로 가입에 실패하였습니다.";
					viewName = "join";
					break;
				case VisiterService.ERROR_DUPLICATE_FOR_EMAIL:
					Message = "동일한 Email이 존재합니다.";
					viewName = "join";
					break;
			}
		req.setAttribute("resultMsg", Message);
		return viewName;
		
	}
	
	 
}
