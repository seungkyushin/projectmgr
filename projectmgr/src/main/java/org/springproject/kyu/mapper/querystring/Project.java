package org.springproject.kyu.mapper.querystring;

public class Project {

	// id,name,email,organization,last_login
		public static final String TABLE_NAME = " project ";
		public static final String COLUMNS = " id, name, sub_description AS subDescription, url, description,"
											+ "file_id AS fileId ";

		/************************************ SELECT ***********************************************/
		public static final String SELECT_ALL = "SELECT" + COLUMNS + "from" +  TABLE_NAME;
		public static final String SELECT_BY_ID = SELECT_ALL + "WHERE id=#{id}";
		/*******************************************************************************************/
	
		
		/************************************ DELETE ***********************************************/
		/*******************************************************************************************/
		
		/************************************ UPDATE ***********************************************/
		/*******************************************************************************************/
			
		/************************************ INSERT ***********************************************/
		/*******************************************************************************************/
	

}
