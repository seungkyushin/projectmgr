package org.springproject.kyu.mapper;

import static org.springproject.kyu.mapper.querystring.Log.*;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springproject.kyu.dto.LogDto;

public interface LogMapper {

	@Select(SELECT_ALL)
	public List<LogDto> getAll();
	
	@Select(SELECT_BY_VISITER_EMAIL)
	public List<LogDto> getByVisiterEmail(@Param("email") String email);

	@Insert(INSERT)
	public int add(LogDto data);
}
