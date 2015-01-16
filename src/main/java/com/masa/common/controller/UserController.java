package com.masa.common.controller;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masa.common.session.Session;
import com.masa.common.session.SessionBank;
import com.masa.common.session.User;
import com.masa.common.googleplus.GoogleAuth;
import com.masa.common.rest.UserAuthReq;
import com.masa.common.rest.UserReq;
import com.masa.common.rest.UserResp;
import com.masa.common.googleplus.GoogleSignIn;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController { 
			
	@RequestMapping(method=RequestMethod.GET, value="/user")
	    public String doGet(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        HttpSession httpSession
	        ) {

		UserResp ret = new UserResp();
		
		Session session = null;
		session = SessionBank.getSession(httpSession.getId());	

		String state = new BigInteger(130, new SecureRandom()).toString(32);

		if (session == null) {
		    session = new Session(httpSession.getId(), null, state);
		    SessionBank.addSession(session);
		} else {
		    session.setState(state);
		}
		    
		if (session.getUserId() != null) {
		    User u = SessionBank.findUser(session.getUserId());
            ret.setUser(u);
		}

		GoogleSignIn googleSignIn = new GoogleSignIn(
			com.masa.common.config.googleplus.Constants.CLIENT_ID,
			state
		);
		ret.setGoogleSignIn(googleSignIn);
			
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

			return mapper.writeValueAsString(ret);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/user")
		public String update(
		    @RequestBody UserReq userReq,
		    HttpServletRequest req,
		    HttpServletResponse resp,
		    HttpSession httpSession
			) {
		
		String nickname = userReq.getNickname();
		
		Session session = null;
		session = SessionBank.getSession(httpSession.getId());
		
		if (session == null || session.getUserId() == null) {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		
		User u = SessionBank.findUser(session.getUserId());
		
		if (u == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		// Renew the Http session to prevent a session fixation attack.
		httpSession.invalidate();
		httpSession = req.getSession();
		session.setSessionId(httpSession.getId());
		
		u.setNickname(nickname);
		UserResp ret = new UserResp();
		ret.setUser(u);
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

			return mapper.writeValueAsString(ret);
	   
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/user/auth")
    public String doPut(
        @RequestBody UserAuthReq userReq,
        HttpServletResponse resp,
        HttpSession httpSession) {
		
		return GoogleAuth.doPut(userReq, resp, httpSession);
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/user/auth")
	public void doDelete(
		HttpServletResponse resp,
		HttpSession httpSession) {
		
		GoogleAuth.doDelete(resp, httpSession);
	}
}