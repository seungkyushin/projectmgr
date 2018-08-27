package org.springproject.kyu.service;

import java.util.List;

import org.springproject.kyu.dto.UserCommentDto;

public interface UserCommentService {

	public List<UserCommentDto> getUserCommentByProjectId(int id, int start);
	public List<UserCommentDto> getAllUserCommentByProjectId(int id);
	public List<UserCommentDto> getUserCommentByVisiterId(int id, int start);
	public int addUserComment(UserCommentDto data,String email, String ip);
	public int getUserCommentCount(int projectId );
	public float getUserCommentAvgScore(int projectId);
}
