package org.springproject.kyu.sqlstring;

public class UserComment {

	// id,name,email,organization,last_login
		public static final String TABLE_NAME = "user_comment";
		public static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;
		public static final String SELECT_BY_PROJECT_ID = SELECT_ALL + " WHERE project_id=:id AND show_check='off' limit :start, :limit";
		public static final String SELECT_ALL_BY_PROJECT_ID = SELECT_ALL + " WHERE project_id=:id";
		public static final String SELECT_ALL_BY_VISITER_ID = SELECT_ALL + " WHERE visiter_id=:id AND show_check='off' limit :start, :limit"; 
		public static final String SELECT_COUNT_BY_PROJECT_ID = "SELECT COUNT(*) FROM user_comment WHERE project_id=:ProjectId AND show_check='off'"; 
}
