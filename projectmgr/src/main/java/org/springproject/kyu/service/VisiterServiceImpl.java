package org.springproject.kyu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springproject.kyu.dao.FileInfoDao;
import org.springproject.kyu.dao.VisiterDao;
import org.springproject.kyu.dto.FileInfoDto;
import org.springproject.kyu.dto.VisiterDto;
import org.springproject.kyu.util.Encryption;

@Service
@Transactional(readOnly = false)
public class VisiterServiceImpl implements VisiterService {

	@Autowired
	VisiterDao visiterDao;
	
	@Autowired
	FileInfoDao fileInfoDao;

	@Autowired
	DateFormat dateFormat;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public int addVisiter(VisiterDto data, String ip) throws Exception {
		logger.info("가입시도 | {} | {}", ip, data.getEmail());

		try {
			// < 패스워드 암호화
			String encryption = Encryption.SHA512(data.getPassword());
			data.setPassword(encryption);
			//< 기본값 설정
			data.setCreateDate(dateFormat.format(new Date()));
			data.setFileId(4);
			
			visiterDao.insert(data);
			
			return SUCCESS;
		} catch (DuplicateKeyException e) {
			logger.error("가입실패:동일 Email 존재 | {} | {} | {}", ip, data.getEmail(), e.toString());
			return ERROR_DUPLICATE_FOR_EMAIL;
		} 
	}

	@Override
	public int deleteVisiter(VisiterDto data, String ip) throws Exception{
		VisiterDto visiter = this.getVisiter(data.getEmail());

		if (visiter != null) {
			visiterDao.delete(data.getEmail());
			logger.error("삭제 성공 | {} | {}", ip, data.getEmail());
			return SUCCESS;
		}
		logger.error("삭제 실패 계정 NULL | {} | {}", ip, data.getEmail());
		return FAILED;
	}

	@Override
	public VisiterDto checkLogin(String email, String password, String ip) throws Exception{
		logger.info("로그인 시도 | {} | {}", ip, email);
		
		VisiterDto visiter = this.getVisiter(email);
		
		if (visiter != null && checkPassword(password, visiter.getPassword()) == true) {
			// < 마지막 로그인 갱신
			visiterDao.updateLastLoginTime(visiter);
			logger.info("로그인 성공 | {} | {}", ip, visiter.getEmail());
			return visiter;
		}
		logger.info("로그인실패 | {} | {}", ip, email);

		return null;
	}

	@Override
	public VisiterDto getVisiter(String email) throws Exception{

		if (email != null && email.isEmpty() == false) {
			if (visiterDao != null)
				try {
					return visiterDao.selectByEmail(email);
				} catch (EmptyResultDataAccessException e) {
					logger.error("방문자 email 일치하는 데이터 없음 | {}", e.toString());
				} 
		}

		return null;
	}

	@Override
	public VisiterDto getVisiter(int id) throws Exception
	{
		if (id != 0) 
		{
			if (visiterDao != null)
			{
				return visiterDao.selectById(id);
			}
		}
		return null;
	}

	@Override
	public int updateVisiter(VisiterDto data, MultipartFile file,  String ip) throws Exception {

		if ( data == null || ip == null || ip.isEmpty() ) {
			return FAILED;
		}
			
			try {
				VisiterDto visiter = this.getVisiter(data.getEmail());
				int beforFileId = visiter.getFileId();
					
				//< 사용자가 선택한 이미지 파일 업로드 및 db 갱신
				if( file.getSize() > 0 || file.getOriginalFilename().isEmpty() == false ) {
					visiter.setFileId( this.uploadImage(file, data.getEmail(), ip) );
				}
					
				//< 패스워드 값 있으면 해당값으로 변경
				if( data.getPassword() != null && data.getPassword().isEmpty() == false) {
					visiter.setPassword( Encryption.SHA512(data.getPassword()) );
				}
				
				//< 조직란에 값이 있으면 해당값으로 변경
				if( data.getOrganization() != null && data.getOrganization().isEmpty() == false) {
					visiter.setOrganization( data.getOrganization() );
				}
				
				//< 회원 정보 수정
				visiterDao.updateInfo(visiter);
				
								
				//< 회원 정보 수정 완료 후 프로파일 이미지 삭제 
				//< 이전 이미지가 default 이미지가 아니라면 해당 파일삭제 및 DB 데이터 삭제
				if( beforFileId != 4 ) {
					FileInfoDto fileInfo = fileInfoDao.selectById(beforFileId);
					String filePath = fileInfo.getSavePath() + "\\" +  fileInfo.getName();
					if( this.delectImage(filePath, data.getEmail(), ip) == SUCCESS) {
						//< 파일삭제 성공 후 db 삭제
						int result = fileInfoDao.delete(fileInfo.getId());
						System.out.println("delete file id " + result);
					}
				}
		
				logger.info("회원정보 수정 성공 | {} | {}", ip, data.getEmail());
				
			} catch (EmptyResultDataAccessException e) {
				logger.error("회원정보 수정 실패 : 알수없는 Email | {} | {} | {}", ip, data.getEmail(), e.getMessage());
				return FAILED;
			} 
		
		return SUCCESS;
	}

	@Override
	public boolean checkPassword(String newPwd, String originalPwd) {
		return originalPwd.equals(Encryption.SHA512(newPwd));
	}

	@Override
	public int uploadImage(MultipartFile file, String email, String ip) throws Exception{
		
		int randomName = Math.abs( new Date().hashCode() );
	    int index = file.getOriginalFilename().lastIndexOf(".");
      	String fileNameExtension =  file.getOriginalFilename().substring(index + 1);
		String fileName = randomName + "." +fileNameExtension;
		//String filePath = "C:\\Users\\kyu\\git\\projectmgr\\projectmgr\\src\\main\\webapp\\images\\upload\\";
		String filePath = "C:\\Users\\Administrator\\git\\projectmgr\\projectmgr\\src\\main\\webapp\\images\\upload\\";
		String fullFilePath = filePath + "\\" + fileName;
		String urlPath = "images/upload/" + fileName;
        
		try(
        	
              FileOutputStream fos = new FileOutputStream(fullFilePath);
              InputStream is = file.getInputStream();
			){
        	    int readCount = 0;
        	    byte[] buffer = new byte[1024];
        	    
        	    while((readCount = is.read(buffer)) != -1){
        	    	fos.write(buffer,0,readCount);
            }
        }catch(Exception ex){
        	logger.error("파일 업로드 실패 | {} | {} | {}", ip, email, ex.getMessage());
            return FAILED;
        }
        
        logger.error("파일 업로드 성공 | {} | {} | {}", ip, email, fullFilePath);
        
        FileInfoDto fileInfo = new FileInfoDto();
        fileInfo.setSavePath(filePath);
        fileInfo.setUrlPath(urlPath);
        fileInfo.setType(file.getContentType());
        fileInfo.setName(fileName);
        fileInfo.setCreateDate(dateFormat.format(new Date()));
          
        return fileInfoDao.insert(fileInfo);
	}

	@Override
	public int delectImage(String filePath, String email, String ip) {
		
		 File file = new File(filePath);
         
	        if( file.exists() ){ //파일존재여부확인
	           if(file.delete()){
	        	   logger.info("파일 삭제 성공 | {} | {} | {}", ip, email , filePath);
	        	   return SUCCESS;
                }else{
                   logger.info("파일 삭제 실패 | {} | {} | {}", ip, email , filePath);
                   return FAILED;
                }
	             
	        }else{
	        	logger.info("파일이 존재하지 않습니다. | {} | {} | {}", ip, email , filePath);
	        	return FAILED;
	        }
	             
		
	}

}
