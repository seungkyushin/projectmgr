package org.springproject.kyu.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
	
	
	/* id값에 따라 DB에 저장된 project들의 정보를 받아온다.
	 * id값이 0일 경우 모든 project 정보를 받아 온다.
	 */
	@GetMapping(path="/project/{id}")
	public Map<String, Object> getProjectList(@PathVariable("id") int id){
		Map<String, Object> resultMap = new HashMap<>();
		
		if( id == 0 ) 
			resultMap.put("projectList", projectService.getProjectListAll());
		else 
			resultMap.put("projectList", projectService.getProjectList(id));

		return resultMap;
	}

	@GetMapping(path="/comment/{projectId}/{start}")
	public Map<String, Object> getCommentList(@PathVariable("projectId") int projectId,
											  @PathVariable("start") int start){
		
		Map<String, Object> resultMap = new HashMap<>();
		List<Object> paramList = new ArrayList<>();
		
		List<UserCommentDto> userCommentDto = userCommentService.getUserCommentByProjectId(projectId,start);
		
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
		resultMap.put("avgScore",userCommentService.getUserCommentAvgScore(projectId) );
		resultMap.put("currentPage",start );
		
		return resultMap;
	}
	
	
/*	@GetMapping(path="/upload")
	public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file){
		
		Map<String, Object> resultMap = new HashMap<>();
		
		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 크기 : " + file.getSize());
		
        try(
                // 맥일 경우 
                //FileOutputStream fos = new FileOutputStream("/tmp/" + file.getOriginalFilename());
                // 윈도우일 경우
                FileOutputStream fos = new FileOutputStream("c:/tmp/" + file.getOriginalFilename());
                InputStream is = file.getInputStream();
        ){
        	    int readCount = 0;
        	    byte[] buffer = new byte[1024];
            while((readCount = is.read(buffer)) != -1){
                fos.write(buffer,0,readCount);
            }
        }catch(Exception ex){
            throw new RuntimeException("file Save Error");
        }

        resultMap.put("", value)
		
		
		return resultMap;
	}*/
	

	//< REST API의 Exception을 관리한다.
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String handleEmptyResultDataAccessException() {
		System.out.println("EmptyResultDataAccessException");
		return "Not Found data";
	}
	
	
}
