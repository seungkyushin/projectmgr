package org.springproject.kyu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springproject.kyu.dto.ProjectDto;
import org.springproject.kyu.dto.UserCommentDto;
import org.springproject.kyu.service.ProjectService;
import org.springproject.kyu.service.UserCommentService;

@Controller
public class UserCommentController {

	@Autowired
	UserCommentService userCommentService;
	
	@Autowired
	ProjectService projectService;
	@GetMapping("/comment")
	public String showPage(@RequestParam(name="projectId") int projectId,
			Model model) throws Exception{
		
		ProjectDto projectDto = projectService.getProjectDto(projectId);
		model.addAttribute("name", projectDto.getName());
		model.addAttribute("projectId", projectDto.getId());
		return "comment";
	}
					
	@PostMapping("/addcomment")
	public ModelAndView addComment(@ModelAttribute UserCommentDto data,
			ModelAndView modelAndView,
			HttpServletRequest req,
			RedirectAttributes redirectAtt,
			HttpSession sec) throws Exception{
		String clientIp = (String)req.getAttribute("clientIp");
		String email = (String)sec.getAttribute("email");
		
		int result = userCommentService.addUserComment(data,email,clientIp);
		if( result != 0 ) {
			modelAndView.setViewName("redirect:descriptionProject?id="+data.getProjectId());
			redirectAtt.addFlashAttribute("resultMsg", "덧글 남겨주셔서 감사합니다.");
		}
		
		return modelAndView;
	}
	

	
}
	