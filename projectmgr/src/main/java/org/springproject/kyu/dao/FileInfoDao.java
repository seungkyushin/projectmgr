package org.springproject.kyu.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springproject.kyu.dto.FileInfoDto;

@Repository
public class FileInfoDao {

	@Autowired
	private SqlSession sqlSession;
	private String namesapce = "org.springproject.kyu.mapper.FileInfoMapper";

	public FileInfoDto selectById(int id) throws EmptyResultDataAccessException, Exception{
		return sqlSession.selectOne(namesapce + ".getById", id);
	}

	public int insert(FileInfoDto data) throws Exception{
		return sqlSession.selectOne(namesapce + ".add", data);

	}

	public int update(FileInfoDto data) throws Exception{
		return sqlSession.selectOne(namesapce + ".update", data);
	}
	
	public int delete(int id) throws Exception{
		return sqlSession.selectOne(namesapce + ".removeById", id);
	}

	/*
	 * @Autowired private NamedParameterJdbcTemplate jdbc; private
	 * RowMapper<FileInfoDto> rowMapper = new
	 * BeanPropertyRowMapper<>(FileInfoDto.class); private SimpleJdbcInsert
	 * insertAction;
	 * 
	 * public FileInfoDao(DataSource dataSource) { this.insertAction = new
	 * SimpleJdbcInsert(dataSource) .withTableName("file_info").
	 * usingGeneratedKeyColumns("id");
	 * 
	 * }
	 * 
	 * public FileInfoDto selectById(int id) { Map<String,Integer> paramMap = new
	 * HashMap<>(); paramMap.put("id", id); return
	 * jdbc.queryForObject("SELECT * FROM file_info WHERE id=:id", paramMap,
	 * rowMapper);
	 * 
	 * } public int insert(FileInfoDto data) { SqlParameterSource params = new
	 * BeanPropertySqlParameterSource(data); return
	 * insertAction.executeAndReturnKey(params).intValue();
	 * 
	 * } public int delete(int id) { Map<String,Integer> paramMap = new HashMap<>();
	 * paramMap.put("id", id); return
	 * jdbc.update("DELETE FROM file_info WHERE id =:id", paramMap); }
	 */

}
