package org.springproject.kyu.service;

import java.util.List;
import java.util.Map;

import org.springproject.kyu.dto.ProjectDto;

public interface ProjectService {
	public List<Map<String,Object>> getProjectListAll() throws Exception;
	public Map<String,Object> getProjectList(int id) throws Exception;
	public ProjectDto getProjectDto(int id) throws Exception;
}
