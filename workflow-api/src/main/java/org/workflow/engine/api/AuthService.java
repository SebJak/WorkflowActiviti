package org.workflow.engine.api;

public interface AuthService {

    public boolean checkAuthentication(String username, String password);
}
