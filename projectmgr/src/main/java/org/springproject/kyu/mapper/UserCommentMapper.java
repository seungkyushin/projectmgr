package org.springproject.kyu.mapper;

import static org.springproject.kyu.mapper.querystring.UserComment.*;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springproject.kyu.dto.CriteriaDto;
import org.springproject.kyu.dto.UserCommentDto;

public interface UserCommentMapper {

		@Select(SELECT_ALL)
		public List<UserCommentDto> getAllList() ;

		@Select(SELECT_ALL_BY_PROJECT_ID)
		public List<UserCommentDto> getAllListByProjectId(CriteriaDto criteria);

		@Select(SELECT_LIMIT_BY_PROJECT_ID)
		public List<UserCommentDto> getListByProjectId(CriteriaDto criteria);

		@Select(SELECT_LIMIT_BY_VISITER_ID)
		public List<UserCommentDto> getListByVisiterId(CriteriaDto criteria);

		@Select(SELECT_COUNT_BY_PROJECT_ID)
		public int getCountByProjectId(CriteriaDto criteria);
		
		@Insert(INSERT)
		public int add(UserCommentDto data);
	
}
