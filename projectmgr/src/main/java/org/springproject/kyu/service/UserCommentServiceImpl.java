package org.springproject.kyu.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springproject.kyu.dao.UserCommentDao;
import org.springproject.kyu.dto.CriteriaDto;
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
	public List<UserCommentDto> getUserCommentByProjectId(CriteriaDto criteria) 
			throws EmptyResultDataAccessException, Exception {
		return userCommentDao.selectByProjectId(criteria);
	}

	@Override
	public List<UserCommentDto> getUserCommentByVisiterId(CriteriaDto criteria) 
			throws EmptyResultDataAccessException, Exception {
		return userCommentDao.selectByVisiterId(criteria);
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
	public List<UserCommentDto> getAllUserCommentByProjectId(int id)
			throws EmptyResultDataAccessException, Exception {
		return userCommentDao.selectAllByProjectId(id);
	}

	@Override
	public float getUserCommentAvgScore(List<UserCommentDto> userCommentList) 
			throws Exception {
	
		float sumScore = 0;
		
		for( UserCommentDto data : userCommentList)
		{
			sumScore += data.getScore();
		}
		
		sumScore /=  userCommentList.size();
		
		return sumScore;
	}

}
