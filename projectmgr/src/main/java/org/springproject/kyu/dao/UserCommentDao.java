package org.springproject.kyu.dao;


import static org.springproject.kyu.mapper.querystring.UserComment.*;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springproject.kyu.dto.UserCommentDto;

@Repository
public class UserCommentDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	
	private SimpleJdbcInsert insertAction;
	private static final int LILIT = 4;
	private RowMapper<UserCommentDto> rowMapper = new BeanPropertyRowMapper<>(UserCommentDto.class);
	
	public UserCommentDao(DataSource dataSource) {
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName(TABLE_NAME)
				.usingGeneratedKeyColumns("id");
		}
	
	public List<UserCommentDto> selectByProjectId(int id,int start){
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("start", start);
		paramMap.put("limit", LILIT);
		return jdbc.query(SELECT_BY_PROJECT_ID, paramMap, rowMapper);
	}
	
	public List<UserCommentDto> selectAllByProjectId(int id){
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return jdbc.query(SELECT_ALL_BY_PROJECT_ID, paramMap, rowMapper);
	}
	
	
	public List<UserCommentDto> selectByVisiterId(int id,int start){
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("start", start);
		paramMap.put("limit", LILIT);
		return jdbc.query(SELECT_ALL_BY_VISITER_ID, paramMap, rowMapper);
	}
	
	public int selectCountByPorjectId(int ProjectId){
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("ProjectId", ProjectId);
		return jdbc.queryForObject(SELECT_COUNT_BY_PROJECT_ID, paramMap, Integer.class);
	}
	
	public int insert(UserCommentDto data) throws SQLException {
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(data);
		return insertAction.executeAndReturnKey(sqlParam).intValue();
	};
}

