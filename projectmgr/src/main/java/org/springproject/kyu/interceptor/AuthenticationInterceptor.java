package org.springproject.kyu.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


//< Ajax도 요청이 들어오면 Interceptor가 동작하게 되기 떄문에 excludePathPatterns를 통해서 제한한다
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//String serveltPath = request.getServletPath();
		HttpSession httpSession = request.getSession();
		
		//< Session에 email 값이 없다면 모든 쿠키를 삭제하고 로그인 페이지로 forward 한다. 
		if( httpSession != null && httpSession.getAttribute("email") == null ) {
			
			Cookie[] cookies = request.getCookies();
			
			if(cookies != null){
				int mas = cookies.length;
				for(int i=1; i<mas; i++){
					if( cookies[i].getName().equals("email") ) {
						cookies[i].setMaxAge(0);
						cookies[i].setPath("/");
						response.addCookie(cookies[i]);
						break;
					}
				}
			}
	
			//< forward를 한 이유는 메시지를 띄우기 위해서
			//< return 값을 false로 주게되면 PostHandle()은 동작하지 않는다.
			RequestDispatcher rd= request.getRequestDispatcher("/login");
			request.setAttribute("resultMsg", "로그인이 필요한 서비스 입니다.");
			rd.forward(request, response);
			return false;
			
		}

		return true;
	}


	
	

}
