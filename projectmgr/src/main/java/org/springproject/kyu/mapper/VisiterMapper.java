package org.springproject.kyu.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springproject.kyu.dto.VisiterDto;

public interface VisiterMapper {

	// id,name,email,organization,last_login
	public static final String TABLE_NAME = "visiter";
	public static final String SELECT_ALL = "SELECT id,name,password,email,organization,"
			+ "file_id as fileId, create_date as createData, last_login_date as lastLoginDate FROM " + TABLE_NAME;
	public static final String SELECT_BY_EMAIL = SELECT_ALL + " WHERE email=#{email}";
	public static final String SELECT_BY_ID = SELECT_ALL + " WHERE id=#{id}";
	public static final String DELETE_BY_EMAIL = "DELETE FROM " + TABLE_NAME + " WHERE email=#{email}";
	public static final String UPDATE_LAST_LOGIN_TIME = "UPDATE " + TABLE_NAME
			+ " set last_login_date=#{lastLoginDate} WHERE email=#{email}";
	public static final String UPDATE_INFO_BY_EMAIL = "UPDATE " + TABLE_NAME
			+ " set password=#{password}, organization=#{organization}, file_id=#{fileId}" + " WHERE email=#{email}";
	public static final String INSERT = "INSERT INTO " + TABLE_NAME
			+ "(name,password,email,organization,file_id,create_date,last_login_date)"
			+ " VALUES(#{name},#{password},#{email},#{organization},#{fileId},#{createDate},#{lastLoginDate})";

	// < 모든 Visiter Data를 확인
	@Select(SELECT_ALL)
	public List<VisiterDto> getAllList() throws SQLException, EmptyResultDataAccessException;

	// < 해당 email의 data를 확인
	@Select(SELECT_BY_EMAIL)
	public VisiterDto getByEmail(@Param("email") String email) throws EmptyResultDataAccessException;

	// < 해당 email의 data를 확인
	@Select(SELECT_BY_ID)
	public VisiterDto getById(@Param("id") int id) throws EmptyResultDataAccessException;

	// < 해당 email의 data를 삭제
	@Delete(DELETE_BY_EMAIL)
	public int removeByEmail(@Param("email") String email) throws SQLException;

	// < 마지막 로그인 시간을 업데이트
	@Update(UPDATE_LAST_LOGIN_TIME)
	public int updateLastLoginByEmail(VisiterDto data)	throws SQLException;
	//@Param("lastLoginDate") String lastLoginDate, @Param("email") String email)
		

	@Update(UPDATE_INFO_BY_EMAIL)
	public int updateInfoByEmail(VisiterDto data) throws SQLException;
			//@Param("password") String password, @Param("organization") String organization,@Param("fileId") int fileId, @Param("email") String email)

	@Insert(INSERT)
	public int add(VisiterDto data) throws SQLException, DuplicateKeyException;

	/*
	 <mapper namespace="org.springproject.kyu.dao.VisiterDao">

	<insert id="create"> INSERT INTO visiter
			  (name, password, email, organization, file_id, create_date, last_login_date)
			VALUES(#{name}, #{password}, #{email}, #{organization}, #{file_id}, #{create_date}, #{last_login_date}) </insert>

 	<select id="read" resultType="org.springproject.kyu.dto.VisterDto">
 		SELECT *  from visiter where email=${email} </select>


	<update id="update"> update tbl_board set title=#{title},
		content=#{content} where bno=#{bno} </update>

	<delete id="delete"> delete from tbl_board where bno=#{bno} </delete>

	
	<select id="listAll" resultType="com.spring.domain.BoardVO">
		<![CDATA[ select bno,title,content,writer,regdate,viewcnt from tbl_board where bno > 0 order by bno desc, regdate desc ]]>
	</select> 

</mapper>*/

	
}
