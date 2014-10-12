package org.workflow.engine.api;

import org.workflow.model.json.JsonResponse;
import org.workflow.model.json.JsonUser;

public interface TaskManagerService {
    
    public JsonResponse getTaskList(JsonResponse jsonResponse, String userName);
    
    public JsonResponse getTaskDetailsByTaskId(JsonResponse jsonResponse, String taskId);
    
    public JsonResponse getTaskDetailsByExecutionId(JsonResponse jsonResponse, String executionId);
    
    public JsonResponse getTaskByUser(JsonResponse jsonResponse, JsonUser user);

}
