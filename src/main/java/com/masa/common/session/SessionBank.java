package com.masa.common.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.masa.common.session.Session;
import com.masa.common.session.User;
import com.masa.common.googleplus.GooglePerson;

public class SessionBank {
    static private List<User> users = new ArrayList<User>();
    static private List<Session> sessions = new ArrayList<Session>();
    
    static public Session getSession(String sessionId) {
    	for (Session s : sessions) {
    		if (s.getSessionId().equals(sessionId)) {
    			return s;
    		}
    	}
    	
    	return null;
    }
    
    static public void addSession(Session session) {
    	sessions.add(session);
    }
    
    static public void addUser(User user) {
    	users.add(user);
    }

    static public User findUser(String userId) {
    	for (User u : users) {
    		if (u.getUserId().equals(userId)) {
    			return u;
    		}
    	}
    	return null;
    }
    
    static public User findUser(GooglePerson gPerson) {
    	for (User u : users) {
    		GooglePerson gp = u.getGooglePerson();
    		if (gp != null &&
    			gp.getId().equals(gPerson.getId()) &&
    			gp.getDisplayName().equals(gPerson.getDisplayName()) &&
    			gp.getEmail().equals(gPerson.getEmail())) {
    			return u;
    		}
    	}
    	return null;
    }
    
    static public void deleteSession(String sessionId) {
    	Iterator<Session> it = sessions.iterator();

    	while (it.hasNext()) {
    		Session s = it.next();
    		
    		if (s.getSessionId().equals(sessionId)) {
    			it.remove();
    		}
    	}
    }

    static public void deleteUser(String userId) {
    	Iterator<User> it = users.iterator();

    	while (it.hasNext()) {
    		User u = it.next();
    		
    		if (u.getUserId().equals(userId)) {
    			it.remove();
    		}
    	}
    }
}
