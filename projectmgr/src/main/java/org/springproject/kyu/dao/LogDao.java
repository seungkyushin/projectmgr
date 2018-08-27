package org.springproject.kyu.dao;

import static org.springproject.kyu.sqlstring.Log.*;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springproject.kyu.dto.LogDto;

@Repository
public class LogDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	private SimpleJdbcInsert insertAction;
	private RowMapper<LogDto> rowMapper = new BeanPropertyRowMapper<>(LogDto.class);
	 
	 public LogDao(DataSource dataSource) {
		  this.insertAction = new SimpleJdbcInsert(dataSource)
				 .withTableName(TABLE_NAME)
				 .usingGeneratedKeyColumns("id");
		 
	 }
	 
	 public List<LogDto> selectAll() {
		  return jdbc.query(SELECT_ALL,rowMapper);
	 }
	 
	 public int insert(LogDto data) {
		 
		 SqlParameterSource params = new BeanPropertySqlParameterSource(data);
			return insertAction.executeAndReturnKey(params).intValue();
	 }
	 
 

}
