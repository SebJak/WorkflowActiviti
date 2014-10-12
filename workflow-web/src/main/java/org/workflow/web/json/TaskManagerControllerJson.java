package org.workflow.web.json;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.workflow.engine.api.AuthService;
import org.workflow.engine.api.TaskManagerService;
import org.workflow.model.json.JsonRequest;
import org.workflow.model.json.JsonResponse;


@Controller
@RequestMapping(value = "/task", produces={"application/json; charset=utf-8"})
public class TaskManagerControllerJson extends ApplicationObjectSupport {

    
    @Autowired
    private TaskManagerService taskManagerService;
    
    @Autowired
    private AuthService authenticationService;
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse defaultHandler(@RequestBody JsonRequest request,
	    HttpServletRequest requestq) {

	JsonResponse response = new JsonResponse();
	response.setAccess(true);
	return response;

    }
    
    @RequestMapping(value = "/getTask", method = RequestMethod.POST)
	public @ResponseBody JsonResponse getTask(@RequestBody JsonRequest request) {
		
	JsonResponse response = new JsonResponse();
	response.setAccess(authenticationService.checkAuthentication(request.getUser().getUserName(), request.getUser().getPassword()));
	if(response.isAccess()){
	    response = taskManagerService.getTaskDetailsByTaskId(response, request.getTask().getId());
	}
	return response;
		
	}
    
    @RequestMapping(value = "/getTaskList", method = RequestMethod.POST)
   	public @ResponseBody JsonResponse getTaskList(@RequestBody JsonRequest request) {
   		
   	JsonResponse response = new JsonResponse();
   	response.setAccess(authenticationService.checkAuthentication(request.getUser().getUserName(), request.getUser().getPassword()));
   	if(response.isAccess()){
   	    response = taskManagerService.getTaskList(response, request.getUser().getUserName());
   	}
   	return response;
   		
   	}
}
