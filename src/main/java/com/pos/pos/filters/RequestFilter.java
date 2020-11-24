package com.pos.pos.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.pos.config.ApplicationConstant;
import com.pos.pos.model.UserAutherationModel;
import com.pos.pos.model.WsResponse;
import com.pos.pos.service.UserAutherationService;



@Component
public class RequestFilter extends OncePerRequestFilter{
	
	
	
	@Autowired
	UserAutherationService userAutherationService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		 String URI = request.getRequestURI();
		System.out.println("URI--"+URI);
		
		 String[] excludeURLs= ApplicationConstant.excludeURL;
		boolean donotCheckURL=false;
		
		for(String excludeURL:excludeURLs) 
		{		
				
			 if(URI.equals(excludeURL) || URI.startsWith(excludeURL) || excludeURL.endsWith(".woff") || excludeURL.endsWith(".ttf")) {
			 donotCheckURL=true;
			 break;
			 }
		}
		
		
		
		 if(donotCheckURL==false) {
		String userToken=request.getHeader("token");
		System.out.println("token--"+userToken);
		
		if(userToken==null || userToken.isEmpty()) {
			
		WsResponse responseObj=new WsResponse("401", "User Authentication Required", null);
		String json = new ObjectMapper().writeValueAsString(responseObj);
		response.getWriter().write(json);
		} else {
			List<UserAutherationModel> userTokensList=userAutherationService.getAllTokens();
			
			boolean userExits=false;
			
			for(UserAutherationModel availableTokens:userTokensList) {
				String availableToken=availableTokens.getUserToken();
				if(availableToken.equals(userToken)) {
					userExits=true;
					break;
				}
			}
			
			
			if(!userExits) {
				WsResponse responseObj=new WsResponse("401", "User Authentication Required", null);
				String json = new ObjectMapper().writeValueAsString(responseObj);
				response.getWriter().write(json);
			}
			else {
				filterChain.doFilter(request, response);
			}
		}
	}//end if Dont check url
		 else {
			 filterChain.doFilter(request, response);
		 }
		 
		 
		 
	}
	
}
