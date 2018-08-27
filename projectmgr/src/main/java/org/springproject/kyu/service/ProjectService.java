package org.springproject.kyu.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springproject.kyu.dto.ProjectDto;

public interface ProjectService {
	
	public List<Map<String,Object>> getProjectListAll();
	public Map<String,Object> getProjectList(int id) ;
	public ProjectDto getProjectDto(int id) ;

}
