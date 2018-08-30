package org.springproject.kyu.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springproject.kyu.dao.LogDao;
import org.springproject.kyu.dto.LogDto;

@Service
public class LogServiceImpl implements LogService{

	@Autowired
	private  DateFormat dateFormat;
	@Autowired
	LogDao logDao;

	@Override
	public int recordLog(String type, String description, String email , String ip) {
		
		LogDto log = new LogDto();
		log.setType(type);
		log.setDescription(description);
		log.setVisiterEmail(email);
		log.setClientIp(ip);
		log.setCreateDate(dateFormat.format(new Date()));
		
		return logDao.insert(log);
		
	}
}
