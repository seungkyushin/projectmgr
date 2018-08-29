package org.springproject.kyu.mapper;

import static org.springproject.kyu.mapper.querystring.FileInfo.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springproject.kyu.dto.FileInfoDto;


public interface FileInfoMapper {

	@Select(SELECT_BY_ID)
	public FileInfoDto getById(@Param("id") int id);
	
	@Delete(DELETE_BY_EMAIL)
	public int removeById(@Param("id") int id);
	
	@Update(UPDATE_INFO)
	public int update(FileInfoDto data);
	
	@Insert(INSERT)
	public int add(FileInfoDto data);
	
}
