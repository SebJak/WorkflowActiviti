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
import org.workflow.engine.api.BugReportingService;
import org.workflow.model.json.JsonResponse;

@Service("bugReporting")
public class BugReportingServiceImpl implements BugReportingService{

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired 
	private TaskService taskService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private IdentityService identityService;
	
	 private static final String fileName ="process/BugReportingWithMail.bpmn20.xml";
	
	@Override
	public void deploy(JsonResponse response) {

		DeploymentQuery deployment = repositoryService.createDeploymentQuery().deploymentName(fileName);
		if(deployment.count()>0)
		    repositoryService.deleteDeployment(deployment.singleResult().getId(), true);
		System.out.println(deployment.count());
		if(deployment.count()<=0){
			repositoryService.createDeployment().addClasspathResource(fileName).name(fileName).deploy();
		}
		System.out.println("Number of process definitions: " +  repositoryService.createProcessDefinitionQuery().count());
	}
	@Override
	public void startProcess(JsonResponse response,String projekt, String version, String message, Date dueDate,String title, String userName) {
	    Map<String, Object> variables = new HashMap<String, Object>();
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
	    
	    variables.put("title", title);
	    variables.put("project", projekt);
	    variables.put("version", version);
	    variables.put("message", message);
	    variables.put("dueDate", simpleDateFormat.format(dueDate));
	    User user = identityService.createUserQuery().userId(userName).singleResult();
	    if(user!=null){
		variables.put("emailReporter", user.getEmail());
	    }
	    if(variables!= null){
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("bugReport", variables);
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		task.setOwner(userName);
		taskService.saveTask(task);
		response.setProcessId(processInstance.getActivityId());
		response.setSuccess(true);
	    }
	}

	@Override
	public void updateBugReport(JsonResponse response,String projekt, String version, String message, Date dueDate, String targetVersion,String priority,String developer,String title, String userName, String taskId){
	    Map<String, Object> variables = new HashMap<String, Object>();
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
	    
	    variables.put("title", title);
	    variables.put("project", projekt);
	    variables.put("version", version);
	    variables.put("message", message);
	    variables.put("targetVersion", targetVersion);
	    variables.put("priority", priority);
	    variables.put("dueDate", simpleDateFormat.format(dueDate));
	    variables.put("developer", developer);
	    User user = identityService.createUserQuery().userId(developer).singleResult();
	    if(user!=null){
		variables.put("emailDeveloper", user.getEmail());
	    }
	    variables.put("teamleaderName", userName);
	    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task!=null){
		    taskService.complete(task.getId(), variables);
		    response.setSuccess(true);
	    }
	    else
		response.setSuccess(false);
			
		
	}
	
	@Override
	public void acceptBug(JsonResponse response,String projekt, String version, String targetVersion,String priority,String summary, String developer, String title, String estimatedTime, String userName, String taskId){
	    Map<String, Object> variables = new HashMap<String, Object>();
	    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
	    
	    variables.put("title", title);
	    variables.put("project", projekt);
	    variables.put("version", version);
	    variables.put("summary", summary);
	    variables.put("targetVersion", targetVersion);
	    variables.put("estimatedTime", estimatedTime);
	    variables.put("priority", priority);
	    variables.put("developer", developer);
	    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task!=null){
		    taskService.complete(task.getId(), variables);
		    response.setSuccess(true);
	    }
	    else
		response.setSuccess(false);
			
		
	}
	
	@Override
	public void resolveBug(JsonResponse response,String projekt, String version, String targetVersion,String priority,String summary, String developer,String resolution,String title, String userName, String taskId){
	    Map<String, Object> variables = new HashMap<String, Object>();
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
	    
	    variables.put("title", title);
	    variables.put("project", projekt);
	    variables.put("version", version);
	    variables.put("summary", summary);
	    variables.put("targetVersion", targetVersion);
	    variables.put("priority", priority);
	    variables.put("resolution", resolution);
	    variables.put("developer", developer);
	    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task!=null){
		    taskService.complete(task.getId(), variables);
		    response.setSuccess(true);
	    }
	    else
		response.setSuccess(false);
			
		
	}
	
	@Override
	public void testFixedBug(JsonResponse response, String titile, String project, String version, String message, boolean fixed, String taskId){
	    Map<String, Object> variables = new HashMap<String, Object>();
	    variables.put("title", titile);
	    variables.put("project", project);
	    variables.put("version", version);
	    variables.put("message", message);
	    variables.put("fixed", fixed);
	    
	    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task!=null){
		    taskService.complete(task.getId(), variables);
		    response.setSuccess(true);
	    }
	    else
		response.setSuccess(false);
	}
	
	
	//przyrwa proces
	@Override	
	public void suspendProcess(JsonResponse response){
		repositoryService.suspendProcessDefinitionByKey("bugReport");
		        
	}
}
