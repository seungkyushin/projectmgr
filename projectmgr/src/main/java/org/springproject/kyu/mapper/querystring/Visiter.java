package org.springproject.kyu.mapper.querystring;

public class Visiter {
	
	// id,name,email,organization,last_login
	public static final String TABLE_NAME = "visiter";
	
	/************************************ SELECT ***********************************************/
	public static final String SELECT_ALL = "SELECT id,name,password,email,organization,"
			+ "file_id as fileId, create_date as createData, last_login_date as lastLoginDate FROM " 
			+ TABLE_NAME;
	public static final String SELECT_BY_EMAIL = SELECT_ALL + " WHERE email=#{email}";
	public static final String SELECT_BY_ID = SELECT_ALL + " WHERE id=#{id}";
	/*******************************************************************************************/
	
	
	/************************************ DELETE ***********************************************/
	public static final String DELETE_BY_EMAIL = "DELETE FROM " + TABLE_NAME + " WHERE email=#{email}";
	/*******************************************************************************************/
	
	
	/************************************ UPDATE ***********************************************/
	public static final String UPDATE_LAST_LOGIN_TIME = "UPDATE " + TABLE_NAME
			+ " set last_login_date=#{lastLoginDate} WHERE email=#{email}";
	public static final String UPDATE_INFO_BY_EMAIL = "UPDATE " + TABLE_NAME
			+ " set password=#{password}, organization=#{organization}, file_id=#{fileId}" 
			+ " WHERE email=#{email}";
	/*******************************************************************************************/
	
	
	/************************************ INSERT ***********************************************/
	public static final String INSERT = "INSERT INTO " + TABLE_NAME
			+ "(name,password,email,organization,file_id,create_date,last_login_date)"
			+ " VALUES(#{name},#{password},#{email},#{organization},#{fileId},#{createDate},#{lastLoginDate})";
	/*******************************************************************************************/
}
