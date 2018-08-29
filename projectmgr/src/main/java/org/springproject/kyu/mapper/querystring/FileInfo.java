package org.springproject.kyu.mapper.querystring;

public class FileInfo {

	// id,name,email,organization,last_login
		public static final String TABLE_NAME = " file_info ";
		public static final String COLUMNS = " id,save_path AS savePath,url_path AS urlPath,"
											+ "type,name,create_date AS createDate ";

		/************************************ SELECT ***********************************************/
		public static final String SELECT_ALL = "SELECT" + COLUMNS + "from" +  TABLE_NAME;
		public static final String SELECT_BY_ID = SELECT_ALL + "WHERE id=#{id}";
		/*******************************************************************************************/
		
		
		/************************************ DELETE ***********************************************/
		public static final String DELETE_BY_EMAIL = "DELETE FROM " + TABLE_NAME + " WHERE id=#{id}";
		/*******************************************************************************************/
		
		
		/************************************ UPDATE ***********************************************/
		public static final String UPDATE_INFO = "UPDATE " + TABLE_NAME
				+ " set url_path=#{urlPath}, type=#{type}, name=#{name}, create_date=#{ceateData}"
				+ " WHERE id=#{id}";
	
		/*******************************************************************************************/
		
		
		/************************************ INSERT ***********************************************/
		public static final String INSERT = "INSERT INTO " + TABLE_NAME	+ "(" +COLUMNS+ ")" 
				+ " VALUES(#{id},#{savePath},#{urlPath},#{type},#{name},#{createDate}";
		/*******************************************************************************************/
	
}
