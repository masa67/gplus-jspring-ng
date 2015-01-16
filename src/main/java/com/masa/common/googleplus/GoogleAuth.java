package com.masa.common.googleplus;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masa.common.session.Session;
import com.masa.common.session.SessionBank;
import com.masa.common.session.User;
import com.masa.common.rest.UserAuthReq;
import com.masa.common.rest.UserAuthResp;
import com.masa.common.googleplus.GooglePerson;
import com.masa.common.config.googleplus.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;
import com.google.api.services.plus.model.Person.Emails;

public class GoogleAuth {
	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
	
	public static String doPut(
		UserAuthReq userReq,
		HttpServletResponse resp,
		HttpSession httpSession) {
	     
		String state = userReq.getState();
		String code = userReq.getCode();
		User u;
		
		Session session = null;
		session = SessionBank.getSession(httpSession.getId());
		
		if (session == null) {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		
		if (session.getUserId() != null) {
			u = SessionBank.findUser(session.getUserId());
			resp.setStatus(HttpServletResponse.SC_OK);
			return null;
			
		} else {
		
			if (session.getState() != null && !session.getState().equals(state)) {
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			}
			
			GoogleTokenResponse tokenResponse = null;
			
			try {
				tokenResponse = new GoogleAuthorizationCodeTokenRequest(
					TRANSPORT,
					JSON_FACTORY,
					com.masa.common.config.googleplus.Constants.CLIENT_ID,
					com.masa.common.config.googleplus.Constants.CLIENT_SECRET,
					code,
					"postmessage")
				.execute();
				
				GoogleCredential cred = new GoogleCredential()
					.setFromTokenResponse(tokenResponse);
	
				Plus plus = new Plus.Builder(TRANSPORT, JSON_FACTORY, cred)
					.setApplicationName(Constants.APPLICATION_NAME).build();
				
				Person person = plus.people().get("me").execute();			
				List<Emails> emails = person.getEmails();
	
				GooglePerson gPerson = new GooglePerson(
					person.getId(),
					person.getDisplayName(),
					emails.get(0).getValue()
				);
	
				String userId = UUID.randomUUID().toString();
				session.setUserId(userId);
				
				u = SessionBank.findUser(gPerson);
				
				if (u == null) {
					u = new User(userId, null, gPerson);
					SessionBank.addUser(u);
				}
				
				resp.setStatus(HttpServletResponse.SC_OK);
				
							
			} catch (IOException e) {			
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return null;
			}
		}
		
		try {
			UserAuthResp ret = new UserAuthResp(u);
			
			ObjectMapper mapper = new ObjectMapper();
		    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	
		    return mapper.writeValueAsString(ret);
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
    }
	 
	public static void doDelete(
		HttpServletResponse resp,
		HttpSession httpSession) {
		
		Session session = null;
		session = SessionBank.getSession(httpSession.getId());
		
		if (session != null) {
			String userId = session.getUserId();
			if (!userId.isEmpty()) {
				SessionBank.deleteUser(userId);
			}
			session.setUserId(null);
		}
		
		resp.setStatus(HttpServletResponse.SC_OK);
	}
		
}
