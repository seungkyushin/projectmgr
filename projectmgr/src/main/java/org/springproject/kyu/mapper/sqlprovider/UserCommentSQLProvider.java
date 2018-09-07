package org.springproject.kyu.mapper.sqlprovider;

import static org.springproject.kyu.mapper.querystring.UserComment.*;


import java.util.Map;

public class UserCommentSQLProvider {
	
	public String makeSelectQuery(Map<String,Object> params) {
			
		String resultSQL = "";
		String action = (String)params.get("action");
		int projectId = (int)params.get("projectId");
		String searchType = (String)params.get("searchType");
	
		
		
		if( action.equals("ALL") == true ) {
			if( projectId != 0) {
				resultSQL = SELECT_BY_PROJECT_ID;
			}
		}else if( action.equals("LIMIT") == true ) {
			//< 프로젝트 별로 덧글을 가지고온다.
			resultSQL = SELECT_BY_PROJECT_ID;
		
			//< 만약 검색 조건이 있다면 조건 추가!
			if( searchType != null && searchType.equals("") == false ) {
				resultSQL += typeChose(searchType);
			}
			
			resultSQL += LIMIT;
			
		}else if( action.equals("COUNT") == true ) {
			resultSQL = SELECT_COUNT_BY_PROJECT_ID;
		}
		
		return resultSQL;
	}
	
	private String typeChose(String type) {
		String resultStr = "";
		
		switch( type ) {
		case "email":
			resultStr = " AND email = #{keyword} ";
			break;
		case "content":
			resultStr = " AND content LIKE CONCAT('%',#{keyword},'%') ";
			break;
		case "score":
			resultStr = " AND score = #{keyword} ";
			break;
		case "type":
			resultStr = " AND type = #{keyword} ";
			break;
		case "createDate":
			resultStr = " AND creat_date = #{keyword} ";
			break;
			
		}
		
		return resultStr;
	}
}
