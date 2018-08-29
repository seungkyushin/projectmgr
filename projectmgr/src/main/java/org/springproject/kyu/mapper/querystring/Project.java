package org.springproject.kyu.mapper.querystring;

public class Project {

		public static final String TABLE_NAME = " project ";
		public static final String COLUMNS = " id,name,url,description,sub_description as subDescription,"
											+ "file_id as fileId ";

		/************************************ SELECT ***********************************************/
		public static final String SELECT_ALL = "SELECT" + COLUMNS + "FROM" +  TABLE_NAME;
		public static final String SELECT_BY_ID = SELECT_ALL + "WHERE id=#{id}";
		/*******************************************************************************************/
	
		
		/************************************ DELETE ***********************************************/
		/*******************************************************************************************/
		
		/************************************ UPDATE ***********************************************/
		/*******************************************************************************************/
			
		/************************************ INSERT ***********************************************/
		/*******************************************************************************************/
	

}
