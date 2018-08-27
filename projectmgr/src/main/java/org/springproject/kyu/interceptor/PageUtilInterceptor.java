package org.springproject.kyu.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PageUtilInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//< 모든 요청에 대한 IP값을 구하여 request에 담아 둔다.
		//< Controller 부분에서 request.getAttribute("clientIp")를 통해 요청한 IP를 알아 낼수 있다.
		request.setAttribute("clientIp", getClientIP(request));
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//< Controller에서 request 객체에 resultMsg, url String을 담아서 보내오게 된다.
		//< 그 String 값을 다시 Cookie를 생성하여 보내준다.
		//< 이렇게한 이유는 웹페이지에서 String값을 한번만 사용하기 위해서이다.(한번 string값 사용하고 삭제)
		//< request 객체로 념겨 줬을때는 새로고침을 해도 값이 남아 잇어서 일회용 팝업창을 사용할 수 없다.
		if( request.getAttribute("resultMsg") != null ) {
			this.makeCookie("resultMsg",request,response);
		}
		
		if( request.getAttribute("url") != null) {
			this.makeCookie("url",request,response);
		}

	}
	public void makeCookie(String attributeName, 
			HttpServletRequest req, HttpServletResponse res) {
		String resultMsg = (String)req.getAttribute(attributeName);
		Cookie cookie = createCookieMsg(attributeName,resultMsg);
		
		if(cookie != null)
			res.addCookie(cookie);
	}
	
	public Cookie createCookieMsg(String attributeName,String msg) {
		try {
			Cookie resultMsg = null;
					//< 한글 변환
			if(msg.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
				resultMsg = new Cookie(attributeName, 
						URLEncoder.encode(msg,"utf-8"));
				} else {
					resultMsg = new Cookie(attributeName,msg);
				}

				resultMsg.setMaxAge(60*60*10);           // 쿠키의 유효기간
				resultMsg.setPath("/");	//< 쿠키를 다른 도메인에서 공유 하기 위해
				return resultMsg;
				
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	 public String getClientIP(HttpServletRequest request) {

	     String ip = request.getHeader("X-FORWARDED-FOR"); 
	     
	     if (ip == null || ip.length() == 0) {
	         ip = request.getHeader("Proxy-Client-IP");
	     }

	     if (ip == null || ip.length() == 0) {
	         ip = request.getHeader("WL-Proxy-Client-IP");  // 웹로직
	     }

	     if (ip == null || ip.length() == 0) {
	         ip = request.getRemoteAddr() ;
	     }
	     
	     return ip;

	 }

}
