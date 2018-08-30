package org.springproject.kyu.mapper.querystring;

public class UserComment {

		public static final String TABLE_NAME = " user_comment ";
		public static final String COLUMNS = " id, type, content, score, show_check AS showCheck,"
											+ "project_id AS projectId, visiter_id AS visiterId,"
											+ "create_date AS createDate ";

	
		/************************************ SELECT ***********************************************/
		public static final String SELECT_ALL = "SELECT" + COLUMNS + "FROM" +  TABLE_NAME;
		public static final String SELECT_LIMIT_BY_PROJECT_ID = SELECT_ALL + "WHERE project_id=#{id} AND show_check='off'"
																	 + " limit #{start}, #{end}";
		public static final String SELECT_ALL_BY_PROJECT_ID = SELECT_ALL + " WHERE project_id=#{id}";
		public static final String SELECT_LIMIT_BY_VISITER_ID = SELECT_ALL + " WHERE visiter_id=#{id} AND show_check='off'"
																		 + " limit #{start}, #{end}"; 
		public static final String SELECT_COUNT_BY_PROJECT_ID = "SELECT COUNT(*)" + TABLE_NAME + "FROM user_comment WHERE project_id=#{id} AND show_check='off'";
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
				+ "(type,content,score,show_check,project_id,visiter_id,create_date)"
				+ " VALUES(#{type},#{content},#{score},#{showCheck},#{projectId},#{visiterId},#{createDate})";
		/*******************************************************************************************/
		
		
}
