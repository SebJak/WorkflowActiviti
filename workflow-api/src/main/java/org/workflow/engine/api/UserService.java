package org.workflow.engine.api;

import org.workflow.model.json.JsonResponse;

public interface UserService {
    
    public boolean login(String username, String password);
    
    public JsonResponse getUserDetails(JsonResponse response, String userName);

    public JsonResponse getDevelopers(JsonResponse response);
    
    public JsonResponse getUsersFromGroup(JsonResponse response, String groupName);
}
