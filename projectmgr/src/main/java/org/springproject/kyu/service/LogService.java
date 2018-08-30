package org.springproject.kyu.service;

public interface LogService {
	
	public int recordLog(String type, String description, String email , String ip);
}
