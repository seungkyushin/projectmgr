package org.springproject.kyu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springproject.kyu.dao.FileInfoDao;
import org.springproject.kyu.dao.VisiterDao;
import org.springproject.kyu.dto.FileInfoDto;
import org.springproject.kyu.dto.VisiterDto;
import org.springproject.kyu.service.VisiterService;

@Controller
public class ProfileController {

	@Autowired
	VisiterService visiterService;
	
	@Autowired
	FileInfoDao fileInfoDao;
	
	@Autowired
	VisiterDao visiterDao;
	
	@GetMapping(path="/profileCheck")
	public String checkProfile(	) {
		return "checkProfile";
	}
	
	@PostMapping(path="/profile")
	public String showpage(@RequestParam(name="password") String password,
			HttpSession hSession,
			RedirectAttributes redirectAttr,
			ModelMap modelMap) throws Exception{
		String email = (String)hSession.getAttribute("email");
		
		if( password == null || email == null) {
			redirectAttr.addFlashAttribute("errorMessage", "다시한번 시도해주세요 \n 문제가 계속된다면 로그인을 다시하길 바랍니다.");
			return "redirect:checkProfile"; 
		}
		
		VisiterDto visiter = visiterService.getVisiter(email);
		String viewName = "";
		
		if( visiter != null && visiterService.checkPassword( password,visiter.getPassword()) == true ) {
			visiter.setPassword("");
			FileInfoDto fileInfo = fileInfoDao.selectById(visiter.getFileId());
			
			modelMap.addAttribute("visiter", visiter);
			modelMap.addAttribute("fileInfo", fileInfo);
			viewName = "profile";
			
		}else {
			redirectAttr.addFlashAttribute("errorMessage", "비밀번를 다시한번 확인하세요");
			viewName = "redirect:checkProfile";
		}
	
		return viewName; 
	}
	
	
	@PostMapping(path="/modifyProfile")
	public String modifyProfile(@ModelAttribute VisiterDto visiter,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest req,
			RedirectAttributes redirectAttr,
			Model model) throws Exception{

		String ip = (String)req.getAttribute("clientIp");
		String viewName = "";
		
		int resultVisiter = visiterService.updateVisiter(visiter, file, ip);

		if( resultVisiter == VisiterService.SUCCESS) {
			model.addAttribute("resultMsg", "회원 정보가 정상적으로 수정되었습니다.");
			viewName = "main";
			
		}else {
			redirectAttr.addFlashAttribute("resultMsg", "회원 정보 수정에 실패 하였습니다.");
			viewName = "redirect:profile";
		}
			
		return viewName;
	}
	
	@PostMapping(path="/delete")
	public String deleteVisiter(@ModelAttribute VisiterDto visiter,
			HttpServletRequest req,
			ModelMap modelMap) throws Exception{
		String viewName = "";
		String ip = (String)req.getAttribute("clientIp");
		
		int result = visiterService.deleteVisiter(visiter, ip);
		
		if( result == VisiterService.SUCCESS) {
			req.setAttribute("resultMsg", "계정이 정상적으로 삭제되었습니다.");
			viewName = "redirect:main";
		}else {
			req.setAttribute("resultMsg", "계정 삭제에 실패했습니다.");
			FileInfoDto fileInfo = fileInfoDao.selectById(visiter.getFileId());
			visiter.setPassword("");
			modelMap.addAttribute("visiter", visiter);
			modelMap.addAttribute("fileInfo", fileInfo);
			viewName = "profile";
		}
		
		return viewName; 
	}
}
