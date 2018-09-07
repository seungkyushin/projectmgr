package org.springproject.kyu.mapper.querystring;

public class Log {
	
			public static final String TABLE_NAME = " log ";
			public static final String COLUMNS = " id, type, description, visiter_email AS visiterEmail,"
												+ "client_ip AS clientIp, create_date AS createDate";

			/************************************ SELECT ***********************************************/
			public static final String SELECT_ALL = "SELECT" + COLUMNS + "from" +  TABLE_NAME;
			public static final String SELECT_BY_VISITER_EMAIL = SELECT_ALL + "WHERE visiter_email=#{visiterEmail}";
			/*******************************************************************************************/
			
			
			/************************************ DELETE ***********************************************/
			/*******************************************************************************************/
				
			/************************************ UPDATE ***********************************************/
			/*******************************************************************************************/
			
			
			/************************************ INSERT ***********************************************/
			public static final String INSERT = "INSERT INTO " + TABLE_NAME	+ "(" +COLUMNS+ ")" 
					+ " VALUES(#{id},#{type},#{description},#{visiterEmail},#{clientIp},#{createDate}";
			/*******************************************************************************************/

	
}
