package org.springproject.kyu.mapper;

import static org.springproject.kyu.mapper.querystring.FileInfo.*;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springproject.kyu.dto.ProjectDto;

public interface ProjectMapper {

	
	@Select(SELECT_ALL)
	public List<ProjectDto> getAll();
		
	@Select(SELECT_BY_ID)
	public ProjectDto getById(@Param("id") int id);
	
	
}
