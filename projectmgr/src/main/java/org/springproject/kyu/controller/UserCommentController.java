package org.springproject.kyu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			HttpServletRequest req) throws Exception{
		
		ProjectDto projectDto = projectService.getProjectDto(projectId);
		
		req.setAttribute("name", projectDto.getName());
		req.setAttribute("projectId", projectDto.getId());
		return "comment";
	}
					
	@PostMapping("/addcomment")
	public String addComment(@ModelAttribute UserCommentDto data,
			HttpServletRequest req,
			HttpSession sec) throws Exception{
		String clientIp = (String)req.getAttribute("clientIp");
		String email = (String)sec.getAttribute("email");
		
		int reuslt = userCommentService.addUserComment(data,email,clientIp);

		if( reuslt != 0 ) {
			req.setAttribute("url","./descriptionProject?id="+data.getProjectId());
			req.setAttribute("resultMsg","덧글 남겨주셔서 감사합니다. 이전 페이지로 돌아갑니다.");
		}
		
		return "comment";
	}
	

	
	
	
}
	