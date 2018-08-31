package org.springproject.kyu.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springproject.kyu.dao.UserCommentDao;
import org.springproject.kyu.dto.CriteriaDto;
import org.springproject.kyu.dto.UserCommentDto;
import org.springproject.kyu.dto.VisiterDto;
import org.springproject.kyu.service.ProjectService;
import org.springproject.kyu.service.UserCommentService;
import org.springproject.kyu.service.VisiterService;

@RestController
@RequestMapping(path="/api")
public class ApiController {

	@Autowired
	ProjectService projectService;
	@Autowired
	VisiterService visiterService;
	@Autowired
	UserCommentService userCommentService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/* id값에 따라 DB에 저장된 project들의 정보를 받아온다.
	 * id값이 0일 경우 모든 project 정보를 받아 온다.
	 */
	@GetMapping(path="/project/{id}")
	public Map<String, Object> getProjectList(@PathVariable("id") int id) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		
		if( id == 0 ) 
			resultMap.put("projectList", projectService.getProjectListAll());
		else 
			resultMap.put("projectList", projectService.getProjectList(id));

		return resultMap;
	}
	@GetMapping("/test")
	public ResponseEntity<List<VisiterDto>> sendListVisiter(){
		List<VisiterDto> list = new ArrayList<>();
		return new ResponseEntity<>(list,HttpStatus.NOT_FOUND);
	}

	@GetMapping(path="/comment/{projectId}/{start}")
	public Map<String, Object> getCommentList(@PathVariable("projectId") int projectId,
											  @PathVariable("start") int start) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		List<Object> paramList = new ArrayList<>();
		CriteriaDto criteria = new CriteriaDto(projectId,start,UserCommentDao.LIMIT,UserCommentDao.LIMIT);
		
		List<UserCommentDto> userCommentDto = userCommentService.getUserCommentByProjectId(criteria);
		
		for( UserCommentDto data : userCommentDto) {
			VisiterDto visiter = visiterService.getVisiter(data.getVisiterId());
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("visiterEmail", visiter.getEmail());
			paramMap.put("content", data.getContent());
			paramMap.put("type", data.getType());
			paramMap.put("score", data.getScore());
			paramMap.put("date", data.getCreateDate());
			
			paramList.add(paramMap);
		}
		
		resultMap.put("comments", paramList);
		resultMap.put("allCount",userCommentService.getUserCommentCount(projectId) );
		resultMap.put("limit", UserCommentDao.LIMIT );
		resultMap.put("avgScore",userCommentService.getUserCommentAvgScore(userCommentDto) );
		resultMap.put("currentPage",start );
		
		return resultMap;
	}
	
	//< REST API의 Exception을 관리한다.
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(baos);
		e.printStackTrace(pw);
		pw.flush();
		logger.error("{}",baos.toString());
		return "Not Found data";
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(baos);
		e.printStackTrace(pw);
		pw.flush();
		logger.error("{}",baos.toString());
		return "알수없는 오류";
	}
	
	
	
}
