package org.springproject.kyu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

	public static String SHA512(String str) {
		
		/* 간단정리
		 * SHA-512 알고리즘은 Hash Function을 통해 평문(문자열)을 MessageDigest(알수없는 문자열)로 바꿔주는 알고리즘이다.
		 * 단방향 알고리즘이라고도 하며 평문(문자열)을 알면 MessageDigest값을 알수 있지만 MessageDigest값으로 평문(문자열)을 알 수 없다.
		 * SHA-512의 계산은 64bit로 구성된 8개의 초기값과 평문(문자열)을 1024bit로 나눈 값을 압축함수를 통해 계산한다.
		 * 계산된 값은 512bit (64bitx8개)로 나오며 다시 이 값과 평문(문자열)을 1024bit로 나눈 값을 round를 돌아가며 계산한다.
		 * 최종 계산 결과값은 128자리 문자열이 나온다.
		 */

		String resultSHA = "";
		
		try {
			
			//< MessageDigest는 단방향 알고리즘으로 결과값에서 원본값을 추출할 수 없다.
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			/* 문자열 바이트 배열을 통해 Digest를 업데이트 합니다.(1024bit의 블럭 생성)
			 * getBytes 문자열의 byte 값을 뱉어낸다.
			 * 문자열의 byte(8bit)란 문자열에서 하나하나 문자의 값을 나타낸다. 
			 * 'ABC' 라는 문자열을 getBytes()할 경우 [0]:65 [1]:66 [2]:67 이런식으로 값이 나온다.
			 */
			md.update(str.getBytes());
			
			 /*해쉬를 반환합니다. 패딩과같은 최종 작업을 수행하여 해서 계산을 완료합니다.
			  * SHA-512를 할경우 바이트배열 크기는 64개 이다.
			  * 
			  *
			  *
			  */
			byte byteData[] = md.digest();
		
			String show = "";
			System.out.println(byteData.length);
			StringBuffer sb = new StringBuffer();
			for(int i=0; i < byteData.length; i++) {
				//< substring(begin) 함수는 지정 인덱스부터 끝까지 값을 추출 
				int num1 = (byteData[i]&0xff);
				int result = num1 + 0x100;
				int check = 0x100; 
				String tes = Integer.toString(result,16);
				String test = tes.substring(1);
				sb.append(test);
				show += test + " / ";
			}
			
			resultSHA = sb.toString();
			System.out.println(show);
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			resultSHA = null;
		}
	
		return resultSHA;
	}
}
