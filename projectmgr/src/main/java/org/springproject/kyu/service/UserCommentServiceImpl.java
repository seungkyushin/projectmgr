package org.springproject.kyu.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springproject.kyu.dao.UserCommentDao;
import org.springproject.kyu.dto.CommentPageDto;
import org.springproject.kyu.dto.SearchDto;
import org.springproject.kyu.dto.UserCommentDto;
import org.springproject.kyu.dto.VisiterDto;

@Service
public class UserCommentServiceImpl implements UserCommentService{

	@Autowired
	UserCommentDao userCommentDao;
	@Autowired
	VisiterService visiterService;
	@Autowired 
	DateFormat  dateFormat;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public List<UserCommentDto> getUserComments(CommentPageDto CommentPage)
			throws EmptyResultDataAccessException, Exception {
		
			Map<String,Object> params = new HashMap<>();
			
			params.put("action", "LIMIT");
			params.put("projectId", CommentPage.getProjectId());
			params.put("start", CommentPage.getStart());
			params.put("end", UserCommentDao.LIMIT);
	
			return userCommentDao.select(params);
	}

	@Override
	public List<UserCommentDto> getUserComments(CommentPageDto CommentPage, SearchDto search)
			throws EmptyResultDataAccessException, Exception {
		
		Map<String,Object> params = new HashMap<>();
		
		params.put("action", "LIMIT");
		params.put("projectId", CommentPage.getProjectId());
		params.put("start", CommentPage.getStart());
		params.put("end", UserCommentDao.LIMIT);
		
		params.put("searchType", search.getType());
		params.put("keyword", search.getkeyWord());

		return userCommentDao.select(params);
	}

	
	@Override
	public int addUserComment(UserCommentDto data, String email, String ip) throws Exception{
		
		int result = 0;
		VisiterDto visiter = visiterService.getVisiter(email);
		
		data.setVisiterId(visiter.getId());
		data.setCreateDate(dateFormat.format(new Date()));
		
		if(data.getShowCheck() == null ){
			data.setShowCheck("off");
		}
				
		try {
			result = userCommentDao.insert(data);
			logger.info("댓글 등록 | {} | {} | {}", ip, email);
		} catch (SQLException e) {
			logger.error("댓글 등록 실패 | {} | {} | {}", ip, email, e.toString());
		}

		return result;
	}

	@Override
	public int getUserCommentCount(int projectId) throws Exception {
		return userCommentDao.selectCountByPorjectId(projectId);
	}


	@Override
	public float getUserCommentAvgScore(int projectId)throws Exception {
		return userCommentDao.selectScoreAvgByPorjectId(projectId);
	}

	
}
