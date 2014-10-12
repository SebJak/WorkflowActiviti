package org.workflow.engine.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workflow.engine.api.DayOffService;
import org.workflow.model.json.JsonResponse;

@Service("dayOffService")
public class DayOffServiceImpl implements DayOffService {

    @Autowired
    private RuntimeService runtimeService;
	
    @Autowired 
    private TaskService taskService;
	
    @Autowired
    private RepositoryService repositoryService;
    
    @Autowired
    private IdentityService identityService;
    //private static final String fileName = "DayOffRequest.bpmn20.xml";
    
    private static final String fileName ="process/DayOffRequestWithMail.bpmn20.xml";
    
    @Override
    public JsonResponse requestDayOff(JsonResponse jsonResponse,
	    long numberDays, Date startDate, String message, String userName) {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
	Map<String, Object> variables = new HashMap<String, Object>();
	variables.put("numberDays", numberDays);
	variables.put("startDate", simpleDateFormat.format(startDate));
	variables.put("reason", message);
	if(variables!= null){
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("dayOffRequest", variables);
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		//test
		task.setOwner(userName);
		taskService.saveTask(task);
		//
		jsonResponse.setProcessId(processInstance.getActivityId());
		jsonResponse.setSuccess(true);
	}
	return jsonResponse;
    }

    @Override
    public JsonResponse responseDayOff(JsonResponse jsonResponse, String message, boolean accept,String userName, String taskId) {
	Map<String, Object> variables = new HashMap<String, Object>();
	variables.put("message", message);
	variables.put("accepted", accept);
	
	
	Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
	if(task!=null){
	    if(accept){
		    variables.put("recipientVariable", identityService.createUserQuery().userId(task.getOwner()).singleResult().getEmail());
		    taskService.complete(task.getId(), variables);
		}
	    else{
		variables.put("managerMail", identityService.createUserQuery().userId(userName).singleResult().getEmail());
        	    taskService.complete(task.getId(), variables);
        	    Task taskAssign = taskService.createTaskQuery().executionId(task.getExecutionId()).singleResult();
        	    taskService.addCandidateUser(taskAssign.getId(), task.getOwner());
        	    taskAssign.setAssignee(task.getOwner());
        	    taskAssign.setOwner(userName);
        	    taskService.saveTask(taskAssign);
	    }
	    //taskService.addCandidateUser(task.getId(), task.getOwner());
	}
	jsonResponse.setSuccess(true);
	return jsonResponse;
    }

    @Override
    public JsonResponse repeatRequest(JsonResponse jsonResponse, long numberDays, Date startDate, String message,boolean repetRequest, String userName, String taskId) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Map<String, Object> variables = new HashMap<String, Object>();
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task!=null){
		    if(repetRequest){
			variables.put("numberDays", numberDays);
			variables.put("startDate", simpleDateFormat.format(startDate));
			variables.put("reason", message);
			variables.put("repeatRequest", true);
			taskService.complete(task.getId(), variables);
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("dayOffRequest", variables);
			Task taskRequest = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
			taskRequest.setOwner(userName);
			taskService.saveTask(taskRequest);
		    }
		    else{
			User user = identityService.createUserQuery().userId(userName).singleResult();
			variables.put("repeatRequest", false);
			variables.put("username",user.getFirstName()+" "+user.getLastName());
			taskService.complete(task.getId(), variables);
		    }
		   
		    
		    jsonResponse.setSuccess(true);
		    System.out.println("Complete task. Repet request: "+repetRequest);
		}
		else{
		    jsonResponse.setSuccess(false);
		}
	
	return jsonResponse;
    }

    //This method is usend only one when the db is clear or when we change the body of process definition.
    @Override
    public JsonResponse deployProcess(JsonResponse jsonResponse) {
	DeploymentQuery deployment = repositoryService.createDeploymentQuery().deploymentName(fileName);
	//if(deployment.count()<=0){
		repositoryService.createDeployment().addClasspathResource(fileName).name(fileName).deploy();
	//}
	System.out.println("Number of process definitions: " +  repositoryService.createProcessDefinitionQuery().count());
	jsonResponse.setSuccess(true);
	return jsonResponse;
    }

}
