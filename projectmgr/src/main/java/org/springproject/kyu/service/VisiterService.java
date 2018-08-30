package org.springproject.kyu.service;

import org.springframework.web.multipart.MultipartFile;
import org.springproject.kyu.dto.VisiterDto;

public interface VisiterService {
	public static final int SUCCESS = 1;
	public static final int FAILED = 0;
	public static final int ERROR_DUPLICATE_FOR_EMAIL = -1;
	
	public VisiterDto getVisiter(String email) throws Exception;
	public VisiterDto getVisiter(int id) throws Exception;
	public int addVisiter(VisiterDto data,String ip) throws Exception; 
	public int deleteVisiter(VisiterDto data, String ip) throws Exception;
	public int updateVisiter(VisiterDto data, MultipartFile file, String ip) throws Exception;
	
	//< utils
	public VisiterDto checkLogin(String email, String password, String ip) throws Exception;
	public boolean checkPassword(String newPwd ,String originalPwd) throws Exception;
	public int uploadImage(MultipartFile file, String email, String ip) throws Exception;
	public int delectImage(String filePath, String email, String ip) throws Exception;
}
