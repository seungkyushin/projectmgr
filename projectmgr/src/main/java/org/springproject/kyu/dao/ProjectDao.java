package org.springproject.kyu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springproject.kyu.dto.ProjectDto;

@Repository
public class ProjectDao {

	
	@Autowired
	private SqlSession sqlSession;
	private String namesapce = "org.springproject.kyu.mapper.ProjectMapper";

	public List<ProjectDto> selectAll() throws Exception{
		  return sqlSession.selectList(namesapce + ".getAll");
	 }
	 
	 public ProjectDto selectById(int id) throws Exception, EmptyResultDataAccessException{
		  return sqlSession.selectOne(namesapce + ".getById", id);
	 }
	 
	
	
/*	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProjectDto> rowMapper = new BeanPropertyRowMapper<>(ProjectDto.class); 

	public List<ProjectDto> selectAll() {
		  return jdbc.query("SELECT * FROM project ORDER BY id desc",rowMapper);
	 }
	 
	 public ProjectDto selectById(int id){
		  Map<String,Integer> paramMap = new HashMap<>();
		  paramMap.put("id", id);
		  return jdbc.queryForObject("SELECT * FROM project WHERE id=:id",paramMap,rowMapper);
	 }
	 */
	 
}

