package org.springproject.kyu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springproject.kyu.dto.CommentPageDto;
import org.springproject.kyu.dto.UserCommentDto;

@Repository
public class UserCommentDao {

	@Autowired
	private SqlSession sqlSession;
	private String namesapce = "org.springproject.kyu.mapper.UserCommentMapper";
	public static final int LIMIT = 5;
	public static final int GROUPPAGE_LIMIT = 3;
	

	public List<UserCommentDto> select(Map<String,Object> params) 
			throws EmptyResultDataAccessException, Exception{
		return sqlSession.selectList(namesapce + ".getRecoreds",params);
	}
	
	public int selectCountByPorjectId(int ProjectId) throws Exception{
		return sqlSession.selectOne(namesapce + ".getCountByProjectId", ProjectId);
	}
	
	public float selectScoreAvgByPorjectId(int ProjectId) throws Exception{
		return sqlSession.selectOne(namesapce + ".getScoreAvgByProjectId", ProjectId);
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

