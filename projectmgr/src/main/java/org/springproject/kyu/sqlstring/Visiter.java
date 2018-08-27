package org.springproject.kyu.sqlstring;

public class Visiter {
	
	// id,name,email,organization,last_login
	public static final String TABLE_NAME = "visiter";
	public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;
	public static final String SELECT_BY_EMAIL = SELECT_ALL + " WHERE email=:email";
	public static final String SELECT_BY_ID = SELECT_ALL + " WHERE id=:id";
	public static final String DELETE_BY_EMAIL = "DELETE FROM " + TABLE_NAME + " WHERE email=:email";
	public static final String UPDATE_LAST_LOGIN_TIME = "UPDATE " + TABLE_NAME + " set last_login_date=:lastLoginDate WHERE email=:email";
	public static final String UPDATE_INFO_BY_EMAIL = "UPDATE " + TABLE_NAME +
			" set password=:password, organization=:organization, file_id=:fileId"
			+ " WHERE email=:email";
}
