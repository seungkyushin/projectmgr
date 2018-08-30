package org.springproject.kyu.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springproject.kyu.dto.CriteriaDto;
import org.springproject.kyu.dto.UserCommentDto;

public interface UserCommentService {

	public List<UserCommentDto> getUserCommentByProjectId(CriteriaDto criteria) throws EmptyResultDataAccessException, Exception;
	public List<UserCommentDto> getAllUserCommentByProjectId(int id) throws EmptyResultDataAccessException, Exception;
	public List<UserCommentDto> getUserCommentByVisiterId(CriteriaDto criteria) throws EmptyResultDataAccessException, Exception;
	public int addUserComment(UserCommentDto data,String email, String ip) throws Exception;
	public int getUserCommentCount(int projectId) throws Exception;
	public float getUserCommentAvgScore(List<UserCommentDto> userCommentList) throws Exception;
}
