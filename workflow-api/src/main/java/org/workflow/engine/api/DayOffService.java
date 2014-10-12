package org.workflow.engine.api;

import java.util.Date;

import org.workflow.model.json.JsonResponse;

public interface DayOffService {

    public JsonResponse requestDayOff(JsonResponse jsonResponse, long numberDazs, Date startDate, String message, String userName);
    
    public JsonResponse responseDayOff(JsonResponse jsonResponse, String message, boolean accept,String userName, String taskId);
    
    public JsonResponse repeatRequest(JsonResponse jsonResponse, long numberDays, Date startDate, String message,boolean repetRequest, String userName, String taskId);

    public JsonResponse deployProcess(JsonResponse jsonResponse);
}
