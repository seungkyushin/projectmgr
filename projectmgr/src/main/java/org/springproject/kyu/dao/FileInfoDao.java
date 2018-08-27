package org.springproject.kyu.dao;

import java.util.HashMap;
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
import org.springproject.kyu.dto.FileInfoDto;

@Repository
public class FileInfoDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<FileInfoDto> rowMapper = new BeanPropertyRowMapper<>(FileInfoDto.class);
	private SimpleJdbcInsert insertAction;
	
	public FileInfoDao(DataSource dataSource) {
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("file_info").
				usingGeneratedKeyColumns("id");
		
	}
	
	public  FileInfoDto selectById(int id) {
			Map<String,Integer> paramMap = new HashMap<>();
			paramMap.put("id", id);
			return jdbc.queryForObject("SELECT * FROM file_info WHERE id=:id", paramMap, rowMapper);
			
	}
	public int insert(FileInfoDto data) {
		 SqlParameterSource params = new BeanPropertySqlParameterSource(data);
		return insertAction.executeAndReturnKey(params).intValue();
		
	}
	public int delete(int id) {
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return jdbc.update("DELETE FROM file_info WHERE id =:id", paramMap);
	}

}
