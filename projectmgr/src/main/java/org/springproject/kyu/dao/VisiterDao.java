package org.springproject.kyu.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springproject.kyu.dto.VisiterDto;

@Repository
public class VisiterDao {

	@Autowired
	 private SqlSession sqlSession;
	 private String namesapce = "org.springproject.kyu.mapper.VisiterMapper";
	 
	 public List<VisiterDto> selectAll() throws EmptyResultDataAccessException, Exception{
		  return sqlSession.selectList(namesapce + ".getAllList");
	 }
	 	    
	 public VisiterDto selectByEmail(String email) throws EmptyResultDataAccessException, Exception{
		 return  sqlSession.selectOne(namesapce + ".getByEmail",email);
	 } 
	 
	 public VisiterDto selectById(int id) throws EmptyResultDataAccessException, Exception {
		 return sqlSession.selectOne(namesapce + ".getById",id);
	 }
	 
	 //< 리턴값은 등록된 ID 값
	 public int insert(VisiterDto data) throws DuplicateKeyException, Exception{
		 return sqlSession.insert(namesapce + ".add", data);
	 }
	 
	 public int delete(String email) throws Exception{
		 return sqlSession.delete(namesapce + ".removeByEmail", email);
	 }
	 
	 public int updateLastLoginTime(VisiterDto data) throws Exception{
		 return sqlSession.update(namesapce + ".updateLastLoginByEmail", data);
	 }
	 
	 public int updateInfo(VisiterDto data) throws Exception{
		 return sqlSession.update(namesapce + ".updateInfoByEmail", data);
	 }
	 
	/*  
	 @Autowired
	 private NamedParameterJdbcTemplate jdbc;
	 private SimpleJdbcInsert insertAction;
	 private RowMapper<VisiterDto> rowMapper = new BeanPropertyRowMapper<>(VisiterDto.class);
	 	 
	 public VisiterDao(DataSource dataSource) {
		 this.insertAction = new SimpleJdbcInsert(dataSource)
				 .withTableName("visiter")
				 .usingGeneratedKeyColumns("id");
		 		 
	 }
	 
	 public List<VisiterDto> selectAll() {
		  return jdbc.query(SELECT_ALL,rowMapper);
	 }
	 
	 public VisiterDto selectByEmail(String email) throws EmptyResultDataAccessException, Exception{
		 Map<String,String> paramMap = new HashMap<>();
		 paramMap.put("email", email);
		 
    	 return jdbc.queryForObject(SELECT_BY_EMAIL, paramMap, rowMapper);
	 } 
	 
	 public VisiterDto selectById(int id) throws EmptyResultDataAccessException{
		 Map<String,Integer> paramMap = new HashMap<>();
		 paramMap.put("id", id);
		 
	 	 return jdbc.queryForObject(SELECT_BY_ID, paramMap, rowMapper);
	 }
	 
	 
	 //< 리턴값은 등록된 ID 값
	 public int insert(VisiterDto data) throws SQLException, DuplicateKeyException{
		 SqlParameterSource params = new BeanPropertySqlParameterSource(data);
		
		 return insertAction.executeAndReturnKey(params).intValue();
	 }
	 
	 public int delete(String email) throws SQLException{
		 Map<String,String> paramMap = new HashMap<>();
		 paramMap.put("email", email);
		 
		 return jdbc.update(DELETE_BY_EMAIL, paramMap);
	 }
	 
	 public int updateLastLoginTime(String email,String lastLoinDate){
		 Map<String,String> paramMap = new HashMap<>();
		 paramMap.put("email", email);
		 paramMap.put("lastLoginDate", lastLoinDate);
		 
		 return jdbc.update(UPDATE_LAST_LOGIN_TIME, paramMap);
	 }
	 
	 public int updateInfo(String email, String password, String organization, int fileId) throws SQLException {
		 Map<String,Object> paramMap = new HashMap<>();
		 paramMap.put("email", email);
		 paramMap.put("password", password);
		 paramMap.put("organization", organization);
		 paramMap.put("fileId", fileId);
		 
		 return jdbc.update(UPDATE_INFO_BY_EMAIL, paramMap);
	 }*/
	

}
