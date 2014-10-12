package org.workflow.engine.api;

import java.util.Date;

import org.workflow.model.json.JsonResponse;

public interface BugReportingService {
    public void deploy(JsonResponse response);

    public void startProcess(JsonResponse response,String projekt, String version, String message, Date duedate,String title, String userName);

    public void suspendProcess(JsonResponse response);

    public void acceptBug(JsonResponse response,String projekt, String version, String targetVersion,String priority,String summary, String developer, String title, String estimatedTime, String userName, String taskId);
    
    public void updateBugReport(JsonResponse response,String projekt, String version, String message, Date dueDate, String targetVersion,String priority,String developer,String title, String userName, String taskId);

    public void resolveBug(JsonResponse response,String projekt, String version, String targetVersion,String priority,String summary, String developer,String resolution,String title, String userName, String taskId);
    
    public void testFixedBug(JsonResponse response, String titile, String project, String version, String message, boolean fixed, String taskId);
}
