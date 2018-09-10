package org.springproject.kyu.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springproject.kyu.dao.UserCommentDao;
import org.springproject.kyu.dto.CommentPageDto;
import org.springproject.kyu.dto.SearchDto;
import org.springproject.kyu.dto.UserCommentDto;
import org.springproject.kyu.dto.VisiterDto;
import org.springproject.kyu.service.ProjectService;
import org.springproject.kyu.service.UserCommentService;
import org.springproject.kyu.service.VisiterService;

@RestController
@RequestMapping(path = "/api")
public class ApiController {

	@Autowired
	ProjectService projectService;
	@Autowired
	VisiterService visiterService;
	@Autowired
	UserCommentService userCommentService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * id값에 따라 DB에 저장된 project들의 정보를 받아온다. id값이 0일 경우 모든 project 정보를 받아 온다.
	 */
	@GetMapping("/projectAllList")
	public Map<String, Object> getProjectList() throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("projectList", projectService.getProjectListAll());
		return resultMap;
	}

	@GetMapping("/test")
	public ResponseEntity<List<VisiterDto>> sendListVisiter() {
		List<VisiterDto> list = new ArrayList<>();
		return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
	}

	@PostMapping(path = "/commentList")
	public Map<String, Object> getCommentList(@ModelAttribute CommentPageDto commentPage) throws Exception {

		// < 요청 페이지 기준으로 시작 레코드 번호를 계산한다. (요청페이지 번호 - 1) * 페이지당 최대 덧글수
		int start = (commentPage.getReqPageNum() - 1) * UserCommentDao.LIMIT;
		commentPage.setStart(start);

		return getUserComments(commentPage, null);
	}

	@PostMapping(path = "/searchCommentList")
	public Map<String, Object> getSearchCommentList(@ModelAttribute CommentPageDto commentPage,
			HttpServletResponse resonse,
			@CookieValue(name = "searchType", required = false) String searchType,
			@CookieValue(name = "keyword", required = false) String keyword) throws Exception {
		
		if( searchType == null || keyword == null )
			return null;
	
		// < 요청 페이지 기준으로 시작 레코드 번호를 계산한다. (요청페이지 번호 - 1) * 페이지당 최대 덧글수
		int start = (commentPage.getReqPageNum() - 1) * UserCommentDao.LIMIT;
		commentPage.setStart(start);
	
		SearchDto search = new SearchDto();
		// < 쿠키값에 저장 되어 있을때
		search.setType(searchType);
		search.setkeyWord(keyword);
			
		return getUserComments(commentPage, search);
	}
	
	
	
	@PostMapping(path = "/searchComment")
	public Map<String, Object> getSearchComment(@ModelAttribute CommentPageDto commentPage,	@ModelAttribute SearchDto search, 
			HttpServletResponse response, HttpServletRequest request,
			@CookieValue(name = "searchType", required = false) String searchType,
			@CookieValue(name = "keyword", required = false) String keyWord) throws Exception {

		// < 요청 페이지 기준으로 시작 레코드 번호를 계산한다. (요청페이지 번호 - 1) * 페이지당 최대 덧글수
		int start = (commentPage.getReqPageNum() - 1) * UserCommentDao.LIMIT;
		commentPage.setStart(start);
		
		if (searchType != null && keyWord != null) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("searchType") == true || cookie.getName().equals("keyword") == true) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}

			}
		}
		

		Cookie cookieType = new Cookie("searchType", search.getType()); // 쿠키를 생성
		cookieType.setMaxAge(60 * 60 * 24); // 쿠키의 유효기간 1일 (86400초)
		cookieType.setPath("/");
		response.addCookie(cookieType);

		Cookie cookieKeyword = new Cookie("keyword", search.getkeyWord()); // 쿠키를 생성
		cookieKeyword.setMaxAge(60 * 60 * 24); // 쿠키의 유효기간 1일 (86400초)
		cookieKeyword.setPath("/");
		response.addCookie(cookieKeyword);
		
		return getUserComments(commentPage, search);
	}
	
	@PostMapping("/clearSearch")
	public Map<String, Object> addComment(@ModelAttribute CommentPageDto commentPage,
			HttpServletRequest request, HttpServletResponse response,
			@CookieValue(name = "searchType", required = false) String searchType,
			@CookieValue(name = "keyword", required = false) String keyWord) throws Exception {

		if (searchType != null && keyWord != null) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("searchType") == true || cookie.getName().equals("keyword") == true) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}

			}

		}

		return getUserComments(commentPage, null);
	}

	
	
	public Map<String, Object> getUserComments( CommentPageDto commentPage, SearchDto search) throws EmptyResultDataAccessException, Exception  {

		Map<String, Object> resultMap = new HashMap<>();
		List<Object> paramList = new ArrayList<>();
		List<UserCommentDto> userComments = null;
		
		/* 현재 프로젝트 기준의 모든 덧글의 수 */
		int commentsAllCount = 0;
		
		if( search == null ) {
			userComments = userCommentService.getUserComments(commentPage);
			
			commentsAllCount = userCommentService.getUserCommentCount(commentPage.getProjectId());

		}else {
			userComments = userCommentService.getUserComments(commentPage,search);
			
			commentsAllCount = userCommentService.getUserCommentCount(commentPage.getProjectId(),search);
		}

		
		/*
		 * 최대 페이지 카운트 모든 덧글 수(프로젝트 기준) / 페이지당 덧글 제한 수 = 최대 페이지 수
		 */
		int MaxPageCount = (int) Math.ceil(((double) commentsAllCount) / UserCommentDao.LIMIT);

		/*
		 * 최대 그룹페이지 카운트 최대 페이지 수 / 그룹페이지당 페이지 제한 수 = 최대 그룹페이지 수
		 */
		int MaxGroupPageCount = (int) Math.ceil(((double) MaxPageCount) / UserCommentDao.GROUPPAGE_LIMIT);

		/*
		 * 현재 그룹페이지 번호 요청한 페이지 번호 / 페이지당 덧글 제한 수 = 현재 그룹페이지 번호
		 */
		int currentGroupPageNum = (int) Math.ceil(((double) commentPage.getReqPageNum()) / UserCommentDao.GROUPPAGE_LIMIT);

		/*
		 * 그룹 페이지당 최대 페이지 카운트 현재 그룹페이지 번호 / 그룹페이지당 페이지 제한 수 = 현재 그룹페이지 번호
		 */
		int limitMaxPageCount = currentGroupPageNum * UserCommentDao.GROUPPAGE_LIMIT > MaxPageCount ? MaxPageCount
				: currentGroupPageNum * UserCommentDao.GROUPPAGE_LIMIT;

		/*
		 * 페이지 시작 인덱스 현재 그룹페이지 번호 / 그룹페이지당 페이지 제한 수 = 현재 그룹페이지 번호
		 */
		int startPageNum = (limitMaxPageCount - UserCommentDao.GROUPPAGE_LIMIT) + 1;
		if (limitMaxPageCount % UserCommentDao.GROUPPAGE_LIMIT > 0) {
			startPageNum += (currentGroupPageNum * UserCommentDao.GROUPPAGE_LIMIT) - limitMaxPageCount;
		}

		for (UserCommentDto findData : userComments) {
			VisiterDto visiter = visiterService.getVisiter(findData.getVisiterId());
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("visiterEmail", visiter.getEmail().substring(0, 4) + "****");
			paramMap.put("content", findData.getContent());
			paramMap.put("type", findData.getType());
			paramMap.put("score", findData.getScore());
			paramMap.put("date", findData.getCreateDate());

			paramList.add(paramMap);
		}

		// < 덧글
		Map<String, Object> paramComment = new HashMap<>();
		paramComment.put("items", paramList);
		paramComment.put("allCount", commentsAllCount);
		resultMap.put("comment", paramComment);

		// < 페이지
		Map<String, Object> paramPage = new HashMap<>();
		paramPage.put("crtNum", commentPage.getReqPageNum());
		paramPage.put("startNum", startPageNum);
		paramPage.put("limitNum", limitMaxPageCount);
		paramPage.put("maxCount", MaxPageCount);
		resultMap.put("page", paramPage);

		// < 페이지 그룹
		Map<String, Object> paramGroupPage = new HashMap<>();
		paramGroupPage.put("crtNum", currentGroupPageNum);
		paramGroupPage.put("maxCount", MaxGroupPageCount);
		paramGroupPage.put("maxLimitCount", UserCommentDao.GROUPPAGE_LIMIT);
		resultMap.put("groupPage", paramGroupPage);

		// < 평균 점수
		resultMap.put("avgScore", userCommentService.getUserCommentAvgScore(commentPage.getProjectId()));

		return resultMap;
	}


	// < REST API의 Exception을 관리한다.
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(baos);
		e.printStackTrace(pw);
		pw.flush();
		logger.error("{}", baos.toString());
		return "NOT FOUND ERROR";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(baos);
		e.printStackTrace(pw);
		pw.flush();
		logger.error("{}", baos.toString());
		return "UNKOWN ERROR";
	}

}
