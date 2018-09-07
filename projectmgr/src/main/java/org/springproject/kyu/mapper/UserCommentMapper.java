package org.springproject.kyu.mapper;

import static org.springproject.kyu.mapper.querystring.UserComment.*;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springproject.kyu.dto.UserCommentDto;
import org.springproject.kyu.mapper.sqlprovider.UserCommentSQLProvider;

public interface UserCommentMapper {

		@Select(SELECT_COUNT_BY_PROJECT_ID)
		public int getCountByProjectId(@Param("projectId") int ProjectId);
		
		@Select(SELECT_SCORE_AVG_BY_PROJECT_ID)
		public float getScoreAvgByProjectId(@Param("projectId") int ProjectId);
		
		@SelectProvider(type=UserCommentSQLProvider.class,method="makeSelectQuery")
		public List<UserCommentDto> getRecoreds(Map<String,Object> params);
		
		@Insert(INSERT)
		public int add(UserCommentDto data);
}
