package org.springproject.kyu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springproject.kyu.dto.LogDto;

@Repository
public class LogDao {
	
	@Autowired
	private SqlSession sqlSession;
	private String namesapce = "org.springproject.kyu.mapper.logMapper";
	
	
	 public List<LogDto> selectAll() throws Exception{
		  return sqlSession.selectList(namesapce + ".getAll");
	 }
	 
	 public int insert(LogDto data) {
		 return sqlSession.insert(namesapce + ".add", data );
	 }
	 
	/*@Autowired
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
	 }*/
	 
 

}
