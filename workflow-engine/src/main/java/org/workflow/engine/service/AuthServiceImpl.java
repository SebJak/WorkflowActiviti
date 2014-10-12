package org.workflow.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workflow.engine.api.AuthService;
import org.workflow.engine.api.UserService;

@Service("authenticationService")
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private UserService userService;
    
    @Override
    public boolean checkAuthentication(String username, String password){
	return userService.login(username, password);
    }

}
