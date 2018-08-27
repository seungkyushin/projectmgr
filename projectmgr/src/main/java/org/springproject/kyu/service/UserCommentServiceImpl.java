package org.springproject.kyu.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springproject.kyu.dao.UserCommentDao;
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
	public List<UserCommentDto> getUserCommentByProjectId(int id, int start) {
		return userCommentDao.selectByProjectId(id,start);
	}

	@Override
	public List<UserCommentDto> getUserCommentByVisiterId(int id, int start) {
		return userCommentDao.selectByVisiterId(id,start);
	}

	@Override
	public int addUserComment(UserCommentDto data, String email, String ip) {
		
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
	public int getUserCommentCount(int projectId) {
		return userCommentDao.selectCountByPorjectId(projectId);
	}

	@Override
	public List<UserCommentDto> getAllUserCommentByProjectId(int id) {
		return userCommentDao.selectAllByProjectId(id);
	}

	@Override
	public float getUserCommentAvgScore(int projectId) {
		List<UserCommentDto> UserCommentList =  getAllUserCommentByProjectId(projectId);
		
		float sumScore = 0;
		
		for( UserCommentDto data : UserCommentList)
		{
			sumScore += data.getScore();
		}
		
		sumScore /=  UserCommentList.size();
		
		return sumScore;
	}

}
