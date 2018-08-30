package org.springproject.kyu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springproject.kyu.dto.CriteriaDto;
import org.springproject.kyu.dto.UserCommentDto;

@Repository
public class UserCommentDao {

	@Autowired
	private SqlSession sqlSession;
	private String namesapce = "org.springproject.kyu.mapper.UserCommentMapper";
	public static final int LIMIT = 5;
	
	public List<UserCommentDto> selectAll() 
			throws EmptyResultDataAccessException, Exception{
		return sqlSession.selectList(namesapce + ".getAllList");
	}
		
	public List<UserCommentDto> selectAllByProjectId(@Param("id") int id) 
			throws EmptyResultDataAccessException, Exception{
		return sqlSession.selectList(namesapce + ".getAllListByProjectId", id);
	}
	
	public List<UserCommentDto> selectByProjectId(CriteriaDto criteria) 
			throws EmptyResultDataAccessException, Exception{
		return sqlSession.selectList(namesapce + ".getListByProjectId", criteria);
	}
	
	public List<UserCommentDto> selectByVisiterId(CriteriaDto criteria) 
			throws EmptyResultDataAccessException, Exception{
		return sqlSession.selectList(namesapce + ".getListByVisiterId", criteria);
	}
	
	public int selectCountByPorjectId(@Param("id") int id) throws Exception{
		return sqlSession.selectOne(namesapce + ".getCountByProjectId", id);
	}
	
	public int insert(UserCommentDto data) throws Exception {
		return sqlSession.insert(namesapce + ".add" , data);
	}
	
/*	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	
	private SimpleJdbcInsert insertAction;
	private static final int LIMIT = 4;
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
	};*/
}

