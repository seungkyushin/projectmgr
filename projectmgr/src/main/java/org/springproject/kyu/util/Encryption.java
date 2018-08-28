package org.springproject.kyu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

	public static String SHA512(String str) {
		
		/* 
		 * 간단정리
		 * SHA-512 알고리즘은 Hash Function을 통해 평문(문자열)을 MessageDigest(알수없는 문자열)로 바꿔주는 알고리즘이다.
		 * 단방향 알고리즘이라고도 하며 평문(문자열)을 알면 MessageDigest값을 알수 있지만 MessageDigest값으로 평문(문자열)을 알 수 없다.
		 * SHA-512의 계산은 64bit로 구성된 8개의 초기값과 평문(문자열)을 1024bit로 나눈 값을 압축함수를 통해 계산한다.
		 * 계산된 값은 512bit (64bitx8개)로 나오며 다시 이 값과 평문(문자열)을 1024bit로 나눈 값을 round를 돌아가며 계산한다.
		 * 최종 계산 결과값은 128자리 문자열이 나온다.
		 * 64bit 컴퓨터에서 계산이 유리하다.
		 */
		try {
			
			//< MessageDigest는 단방향 알고리즘으로 결과값에서 원본값을 추출할 수 없다.
			//< 메시지 다이제스트(Message Digest)란, 메시지를 해시(Hash)하는 것을 의미한다. 
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			/* 
			 * 문자열 바이트 배열을 통해 Digest를 업데이트 합니다.(1024bit의 블럭 생성)
			 * getBytes 문자열의 byte 값을 뱉어낸다.
			 * 문자열의 byte(8bit)란 문자열에서 하나하나 문자의 값을 나타낸다. 
			 * 'ABC' 라는 문자열을 getBytes()할 경우 [0]:65 [1]:66 [2]:67 이런식으로 값이 나온다.
			 */
			md.update(str.getBytes());
			
			 /*
			  *  해쉬를 반환합니다. 패딩과같은 최종 작업을 수행하여 해서 계산을 완료합니다.
			  * SHA-512를 할 경우 바이트배열 크기는 64개가 나오며 1btye 당 8bit 64 * 8 = 512bit 결과값
			  */
			byte resultByteData[] = md.digest();
			
			StringBuffer sb = new StringBuffer();
	
		/*	int max = resultByteData.length;
			for(int i=0; i < max; i++) {
			//< substring(begin) 함수는 지정 인덱스부터 끝까지 값을 추출 
				sb.append(Integer.toString((resultByteData[i]&0xff) + 0x100,16).substring(1));
				
			}*/
			
			/*
			 * 최종 결과값 64btye(512bit)를 1byte(8bit)씩 계산한다. (총 64번 계산)
			 * 1. (bytedata&0xff) : 8bit와 0xff(1111 1111) AND bit 연산을 한다. AND 연산에 의해 양수는 그대로 음수는 양수로 바뀐다.
			 * 2. (bytedata&0xff) + 0x100 : AND 연산된 결과값에  0x100(0001 1111 1111) 256를 더한다.
			 * 3. Integer.toString((bytedata&0xff) + 0x100, 16) : 숫자형 값을 16진수 표기법으로 표현하고 String값으로 변경한다.
			 * 4. Integer.toString((bytedata&0xff) + 0x100, 16).substring(1) : String 값으로 변경된 값의 배열 0번째를 제외하고 출력한다.
			 * 
			 * 이런 계산을 진행하면 1byte당 2자리 문자가 출력되에 결과값의 문자열 길이는 128자리가 된다.
			 */
			for(byte bytedata : resultByteData) {
				//< substring(begin) 함수는 지정 인덱스부터 끝까지 값을 추출 
				sb.append(Integer.toString((bytedata&0xff) + 0x100, 16).substring(1));
				
			}
			return sb.toString();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

	}
}
