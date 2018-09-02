package org.springproject.kyu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springproject.kyu.service.ProjectService;


@Controller
public class DescriptionController {
	
	@Autowired
	ProjectService projectService;

	@GetMapping(path="/descriptionProject")
	public String showDescriptionProjectPage(@RequestParam(name="id") int projectId,
			Model model) throws Exception {
		
		Map<String,Object> project = projectService.getProjectList(projectId);
		model.addAttribute("project", project);
		return "descriptionProject";
	}
	
	
	
	
}
