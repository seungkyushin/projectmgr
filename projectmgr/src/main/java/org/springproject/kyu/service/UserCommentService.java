package org.springproject.kyu.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springproject.kyu.dto.CommentPageDto;
import org.springproject.kyu.dto.SearchDto;
import org.springproject.kyu.dto.UserCommentDto;

public interface UserCommentService {
	
	public List<UserCommentDto> getUserComments(CommentPageDto CommentPage) throws EmptyResultDataAccessException, Exception;
	public List<UserCommentDto> getUserComments(CommentPageDto CommentPage,SearchDto search) throws EmptyResultDataAccessException, Exception;
	public int addUserComment(UserCommentDto data,String email, String ip) throws Exception;
	public int getUserCommentCount(int projectId, SearchDto search ) throws Exception;
	public int getUserCommentCount(int projectId) throws Exception;
	public float getUserCommentAvgScore(int projectId) throws Exception;

}
