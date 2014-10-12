package org.workflow.engine.service;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workflow.engine.api.TaskManagerService;
import org.workflow.engine.api.UserService;
import org.workflow.model.json.JsonResponse;
import org.workflow.model.json.JsonUser;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private IdentityService identityService;
    
    @Autowired
    private TaskManagerService managerService;
    
//    @Autowired
//    private TaskService taskService;
//    
//    @Autowired 
//    private HistoryService historyService;
//    
//    @Autowired 
//    private RuntimeService runtimeService;
    
    @Override
    public boolean login(String username, String password) {
	return identityService.checkPassword(username, password);
    }
    
    @Override
    public JsonResponse getUserDetails(JsonResponse response, String userName){
	User user = identityService.createUserQuery().userId(userName).singleResult();

	JsonUser jsonUser = new JsonUser(user);	
	response = managerService.getTaskByUser(response, jsonUser);
	
	response.setUser(jsonUser);

	response.setSuccess(true);
	
	return response;
    }
    
    @Override
    public JsonResponse getDevelopers(JsonResponse response){
	
	response = getUsersFromGroup(response, "developer");
	response.setSuccess(true);
	return response;
    }
    
    @Override
    public JsonResponse getUsersFromGroup(JsonResponse response, String groupName){
	List<User> users = identityService.createUserQuery().memberOfGroup(groupName).list();
	if(users!=null){
	    List<JsonUser> jsonUsers = new ArrayList<JsonUser>();
	    for(User user: users){
		JsonUser tmp = new JsonUser(user);
		jsonUsers.add(tmp);
		
	    }    
	    response.setUsers(jsonUsers);
	}
	response.setSuccess(true);
	return response;
    }

}
