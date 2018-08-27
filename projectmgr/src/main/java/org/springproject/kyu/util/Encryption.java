package org.springproject.kyu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

	public static String SHA512(String str) {
		String resultSHA = "";
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			md.update(str.getBytes());
			
			byte byteData[] = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for(int i=0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i]&0xff)+ 0x100,16).substring(1));
			}
			
			resultSHA = sb.toString();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			resultSHA = null;
		}
	
		return resultSHA;
	}
}
