package org.springproject.kyu.mapper;

import static org.springproject.kyu.mapper.querystring.Visiter.*;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springproject.kyu.dto.VisiterDto;

public interface VisiterMapper {

	// < 모든 Visiter Data를 확인
	@Select(SELECT_ALL)
	public List<VisiterDto> getAllList();

	// < 해당 email의 data를 확인
	@Select(SELECT_BY_EMAIL)
	public VisiterDto getByEmail(@Param("email") String email);

	// < 해당 email의 data를 확인
	@Select(SELECT_BY_ID)
	public VisiterDto getById(@Param("id") int id, @Param("test") int test);

	// < 해당 email의 data를 삭제
	@Delete(DELETE_BY_EMAIL)
	public int removeByEmail(@Param("email") String email);

	// < 마지막 로그인 시간을 업데이트
	@Update(UPDATE_LAST_LOGIN_TIME)
	public int updateLastLoginByEmail(VisiterDto data);
		
	@Update(UPDATE_INFO_BY_EMAIL)
	public int updateInfoByEmail(VisiterDto data);

	@Insert(INSERT)
	public int add(VisiterDto data);

	/*
	 * XML 설정
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
