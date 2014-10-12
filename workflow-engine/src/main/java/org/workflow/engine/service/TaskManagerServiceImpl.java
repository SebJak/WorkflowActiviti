package org.workflow.engine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workflow.engine.api.TaskManagerService;
import org.workflow.model.json.JsonResponse;
import org.workflow.model.json.JsonTask;
import org.workflow.model.json.JsonUser;

@Service("taskManagerService")
public class TaskManagerServiceImpl implements TaskManagerService{

    @Autowired
    private IdentityService identityService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private RuntimeService runtimeService;
    
    
    @Override
    public JsonResponse getTaskList(JsonResponse jsonResponse, String userName) {
	System.out.println(userName);
	List<Group> groups = identityService.createGroupQuery().groupMember(userName).list();
	List<JsonTask> tasksGroup = new ArrayList<JsonTask>();
	List<JsonTask> tasksUser = new ArrayList<JsonTask>();
	
	    for(Task t: taskService.createTaskQuery().taskCandidateUser(userName).list()){		
		JsonTask jsonTask = new JsonTask(t);
		Map<String, Object> var = runtimeService.getVariables(t.getExecutionId());
		jsonTask.setTaskLocalVariable(var);
		tasksGroup.add(jsonTask);
	    }
	
	List<Task> tasksUserTmp = taskService.createTaskQuery().taskAssignee(userName).list();
	if(tasksUserTmp!=null){
	    for(Task t: tasksUserTmp){
		JsonTask jsonTask = new JsonTask(t);
		Map<String, Object> var = runtimeService.getVariables(t.getExecutionId());
		jsonTask.setTaskLocalVariable(var);
		tasksUser.add(jsonTask);
	    }
	    
	}
	else{
	    System.out.println("Brak tasków");
	}
	jsonResponse.setUserTasks(tasksUser);
	jsonResponse.setGroupTasks(tasksGroup);
	jsonResponse.setSuccess(true);
	return jsonResponse;
    }

    @Override
    public JsonResponse getTaskDetailsByTaskId(JsonResponse jsonResponse,
	    String taskId) {
	Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
	if(task!=null){
        	JsonTask jsonTask = new JsonTask(task);
        	Map<String, Object> var = runtimeService.getVariables(task.getExecutionId());	
        	jsonTask.setTaskLocalVariable(var);
        	jsonResponse.setTask(jsonTask);
	}
	jsonResponse.setSuccess(true);
	return jsonResponse;
    }

    @Override
    public JsonResponse getTaskDetailsByExecutionId(JsonResponse jsonResponse,
	    String executionId) {
	List<Task> tasks = taskService.createTaskQuery().executionId(executionId).list();
	List<JsonTask> jsonTasks = new ArrayList<JsonTask>();
	if(tasks!=null){
	    for(Task t: tasks){
        	JsonTask jsonTask = new JsonTask(t);
        	Map<String, Object> var = runtimeService.getVariables(t.getExecutionId());	
        	jsonTask.setTaskLocalVariable(var);
        	jsonTasks.add(jsonTask);
	    }
	}
	jsonResponse.setTasks(jsonTasks);
	jsonResponse.setSuccess(true);
	return jsonResponse;

    }

    @Override
    public JsonResponse getTaskByUser(JsonResponse jsonResponse, JsonUser jsonUser) {
	List<Group> groups = identityService.createGroupQuery().groupMember(jsonUser.getUserName()).list();
	List<JsonTask> tasksGroup = new ArrayList<JsonTask>();
	List<JsonTask> tasksUser = new ArrayList<JsonTask>();
	
	    for(Task t: taskService.createTaskQuery().taskCandidateUser(jsonUser.getUserName()).list()){		
		JsonTask jsonTask = new JsonTask(t);
		Map<String, Object> var = runtimeService.getVariables(t.getExecutionId());
		jsonTask.setTaskLocalVariable(var);
		tasksGroup.add(jsonTask);
	    }
	
	List<Task> tasksUserTmp = taskService.createTaskQuery().taskAssignee(jsonUser.getUserName()).list();
	if(tasksUserTmp!=null){
	    for(Task t: tasksUserTmp){
		JsonTask jsonTask = new JsonTask(t);
		Map<String, Object> var = runtimeService.getVariables(t.getExecutionId());
		jsonTask.setTaskLocalVariable(var);
		tasksUser.add(jsonTask);
	    }
	    
	}
	else{
	    System.out.println("Brak tasków");
	}
	jsonResponse.setUserTasks(tasksUser);
	jsonResponse.setGroupTasks(tasksGroup);
	jsonResponse.setSuccess(true);
	return jsonResponse;
    }

}
