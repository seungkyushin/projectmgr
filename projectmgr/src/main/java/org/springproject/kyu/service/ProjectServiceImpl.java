package org.springproject.kyu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springproject.kyu.dao.FileInfoDao;
import org.springproject.kyu.dao.ProjectDao;
import org.springproject.kyu.dto.FileInfoDto;
import org.springproject.kyu.dto.ProjectDto;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	FileInfoDao fileInfoDao;
	
	@Override
	public List<Map<String, Object>> getProjectListAll() {
		
		List<ProjectDto> projectDto = projectDao.selectAll();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		for( ProjectDto data : projectDto) {
			Map<String, Object> pramMap = new HashMap<>();
			
			pramMap.put("id", data.getId());
			pramMap.put("description", data.getDescription());
			pramMap.put("subdescription", data.getSubDescription());
			pramMap.put("name", data.getName());
			pramMap.put("url", data.getUrl());
			
			FileInfoDto fileInfoDto = fileInfoDao.selectById(data.getFileId());
			pramMap.put("image", fileInfoDto.getUrlPath());
			
			resultList.add(pramMap);
		}
		
		return resultList;
	}

	@Override
	public Map<String, Object> getProjectList(int id) {
		ProjectDto projectDto = projectDao.selectById(id);
		Map<String, Object> result = new HashMap<>();
			
			result.put("id", projectDto.getId());
			result.put("description", projectDto.getDescription());
			result.put("subdescription", projectDto.getSubDescription());
			result.put("name", projectDto.getName());
			result.put("url", projectDto.getUrl());
			
			FileInfoDto fileInfoDto = fileInfoDao.selectById(projectDto.getFileId());
			result.put("image", fileInfoDto.getUrlPath());
		
		return result;
	}

	@Override
	public ProjectDto getProjectDto(int id) throws EmptyResultDataAccessException{
		return projectDao.selectById(id);
	}
	

}
