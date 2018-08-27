package org.springproject.kyu.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
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
	public int addVisiter(VisiterDto data, String ip) {// throws SQLException, DuplicateKeyException {

	
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
		} catch (SQLException e) {
			logger.error("가입실패 | {} | {} | {}", ip, data.getEmail(), e.toString());
			return FAILED;
		}
	}

	@Override
	public int deleteVisiter(VisiterDto data, String ip) {
		VisiterDto visiter = this.getVisiter(data.getEmail());

		if (visiter != null  && checkPassword(data.getPassword(), visiter.getPassword()) == true) {
			try {
				visiterDao.delete(data.getEmail());
				logger.error("삭제 성공 | {} | {}", ip, data.getEmail());
				return SUCCESS;
			} catch (SQLException e) {
				logger.error("삭제 실패 | {} | {} | {}", ip, data.getEmail(),e.toString());
				return FAILED;
			}
		}
		
		return FAILED;
	}

	@Override
	public VisiterDto checkLogin(String email, String password, String ip) {
		VisiterDto visiter = this.getVisiter(email);
		logger.info("로그인 시도 | {} | {}", ip, email);

		if (visiter != null && checkPassword(password, visiter.getPassword()) == true) {
			// < 마지막 로그인 갱신
			visiterDao.updateLastLoginTime(visiter.getEmail(), dateFormat.format(new Date()));
			logger.info("로그인 성공 | {} | {}", ip, visiter.getEmail());
			return visiter;
		}
		logger.info("로그인실패 | {} | {}", ip, email);

		return null;
	}

	@Override
	public VisiterDto getVisiter(String email) {

		if (email != null && email.isEmpty() == false) {
			if (visiterDao != null)
				try {
					return visiterDao.selectByEmail(email);
				} catch (EmptyResultDataAccessException e) {
					logger.error("결과값 없음 | {}", e.toString());

				} catch (Exception e) {
					logger.error("{}", e.toString());
				}
		}

		return null;
	}

	@Override
	public VisiterDto getVisiter(int id) {
		if (id != 0) {
			if (visiterDao != null)
				return visiterDao.selectById(id);
		}

		return null;
	}

	@Override
	public int updateVisiter(VisiterDto data, MultipartFile file,  String ip) {

		if ( data == null || ip == null || ip.isEmpty() ) {
			return FAILED;
		}

			/*
			 * 1. 파일 upload 수행 -> 실패시 error 반환 성공시 fileInfo db에 insert
			 * 2. insert 수행 -> 실패시 upload 파일 삭제 성공시 visiter 정보 변경
			 * 3. visiter 정보 변경 수행 -> password는 등록시 변경, origain 등록시 변경, file 업로드 했으면
			 * 파일정보 갱신 실패시 1,2,3 롤백
			 * */
			
			try {
				VisiterDto visiter = visiterDao.selectByEmail(data.getEmail());
				
				//< fileid 갱신
				int fileId = 0;
				if( file.getSize() > 0 || file.getOriginalFilename().isEmpty() == false ) {
					fileId = this.uploadImage(file, data.getEmail(), ip);
				}else {
					fileId = visiter.getFileId();
				}
				
				String password = "";
				if( data.getPassword() != null && data.getPassword().isEmpty() == false) {
					password = Encryption.SHA512(data.getPassword());
				}else {
					password = visiter.getPassword();
				}
				
				//< 업로드 테스트 

				visiterDao.updateInfo(visiter.getEmail(), password, data.getOrganization(), fileId);
				logger.info("회원정보 수정 성공 | {} | {}", ip, data.getEmail());
				
			} catch (EmptyResultDataAccessException e) {
				logger.error("회원정보 수정 실패 : 알수없는 Email | {} | {} | {}", ip, data.getEmail(), e.toString());
				return FAILED;
			}  catch (SQLException e) {
				logger.error("회원정보 수정 실패 | {} | {} | {}", ip, data.getEmail(), e.toString());
				return FAILED;
			}
			catch (Exception e) {
				logger.error("회원정보 수정 실패 | {} | {} | {}", ip, data.getEmail(), e.toString());
				return FAILED;
			}
			
		return SUCCESS;
	}

	@Override
	public boolean checkPassword(String newPwd, String originalPwd) {
		return originalPwd.equals(Encryption.SHA512(newPwd));
	}

	@Override
	public int uploadImage(MultipartFile file, String email, String ip) {

		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 크기 : " + file.getSize());
		
		
		int randomName = new Date().hashCode();
	    int index = file.getOriginalFilename().lastIndexOf(".");
      	String fileNameExtension =  file.getOriginalFilename().substring(index + 1);
		String fileName = randomName + "." +fileNameExtension;
		String filePath = "C:/Users/kyu/git/projectmgr/projectmgr/src/main/webapp/images/upload/" + fileName;
        String savePath = "images/upload/" + fileName;
		try(
        	
                FileOutputStream fos = new FileOutputStream(filePath);
                InputStream is = file.getInputStream();
        ){
        	    int readCount = 0;
        	    byte[] buffer = new byte[1024];
            while((readCount = is.read(buffer)) != -1){
                fos.write(buffer,0,readCount);
            }
        }catch(Exception ex){
        	logger.error("파일 업로드 실패 | {} | {} | {}", ip, email, ex.toString());
            return FAILED;
        }
        
        logger.error("파일 업로드 성공 | {} | {} | {}", ip, email, filePath);
        
        FileInfoDto fileInfo = new FileInfoDto();
        fileInfo.setSavePath(savePath);
        fileInfo.setType(file.getContentType());
        fileInfo.setName(fileName);
        fileInfo.setCreateDate(dateFormat.format(new Date()));
          
        return fileInfoDao.insert(fileInfo);
	}

}
