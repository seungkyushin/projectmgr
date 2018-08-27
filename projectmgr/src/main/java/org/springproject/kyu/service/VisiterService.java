package org.springproject.kyu.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springproject.kyu.dto.VisiterDto;
public interface VisiterService {

	public static final int SUCCESS = 1;
	public static final int FAILED = 0;
	public static final int ERROR_DUPLICATE_FOR_EMAIL = -1;
	
	public VisiterDto getVisiter(String email);
	public VisiterDto getVisiter(int id);
	public int addVisiter(VisiterDto data,String ip); 
	public int deleteVisiter(VisiterDto data, String ip);
	public int updateVisiter(VisiterDto data, MultipartFile file, String ip);
	
	//< utils
	public VisiterDto checkLogin(String email, String password, String ip);
	public boolean checkPassword(String newPwd ,String originalPwd);
	public int uploadImage(MultipartFile file, String email, String ip);
	public int delectImage(String filePath, String email, String ip);
}
