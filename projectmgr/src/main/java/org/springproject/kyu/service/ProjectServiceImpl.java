package org.springproject.kyu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<Map<String, Object>> getProjectListAll() throws Exception{
		
		List<ProjectDto> projectDto = projectDao.selectAll();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		for( ProjectDto data : projectDto) {
			Map<String, Object> pramMap = new HashMap<>();
			
			pramMap.put("id", data.getId());
			pramMap.put("description", data.getDescription());
			pramMap.put("subdescription", data.getSubDescription());
			pramMap.put("name", data.getName());
			pramMap.put("url", data.getUrl());
			
			FileInfoDto fileInfoDto = null;
			
			try {
				fileInfoDto = fileInfoDao.selectById(data.getFileId());
				pramMap.put("image", fileInfoDto.getUrlPath());
			}  catch (EmptyResultDataAccessException e) {
				logger.error("{}",e.toString());
				pramMap.put("image", "images/notfoundImage.png");
			} 
			
			
			resultList.add(pramMap);
		}
		
		return resultList;
	}

	@Override
	public Map<String, Object> getProjectList(int id) throws Exception{
		ProjectDto projectDto = projectDao.selectById(id);
		Map<String, Object> result = new HashMap<>();
			
			result.put("id", projectDto.getId());
			result.put("description", projectDto.getDescription());
			result.put("subdescription", projectDto.getSubDescription());
			result.put("name", projectDto.getName());
			result.put("url", projectDto.getUrl());
			
			FileInfoDto fileInfoDto = null;
			try {
					fileInfoDto = fileInfoDao.selectById(projectDto.getFileId());
					result.put("image", fileInfoDto.getUrlPath());
			} catch (EmptyResultDataAccessException e) {
				logger.error("{}",e.toString());
				result.put("image", "images/notfoundImage.png");
			}
			
		
		return result;
	}

	@Override
	public ProjectDto getProjectDto(int id) throws Exception{
		return projectDao.selectById(id);
	}
	

}
