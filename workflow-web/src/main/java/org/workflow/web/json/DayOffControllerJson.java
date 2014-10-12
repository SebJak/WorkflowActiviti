package org.workflow.web.json;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.workflow.engine.api.AuthService;
import org.workflow.engine.api.DayOffService;
import org.workflow.model.json.JsonRequest;
import org.workflow.model.json.JsonResponse;
@Controller
@RequestMapping(value="/dayoff", produces={"application/json; charset=utf-8"})
public class DayOffControllerJson extends ApplicationObjectSupport {

    @Autowired
    private AuthService authenticationService;
    
    @Autowired
    private DayOffService dayOffService;
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse defaultHandler(@RequestBody JsonRequest request,
	    HttpServletRequest requestq) {

	JsonResponse response = new JsonResponse();
	response.setAccess(true);
	return response;

    }
    
    @RequestMapping(value = "/deploy", method = RequestMethod.POST)
	public @ResponseBody JsonResponse deploynHandler(@RequestBody JsonRequest request) {
		
	JsonResponse response = new JsonResponse();
	response.setAccess(authenticationService.checkAuthentication(request.getUser().getUserName(), request.getUser().getPassword()));
	if(response.isAccess()){
	    dayOffService.deployProcess(response);
	}
	return response;
		
	}
    
    @RequestMapping(value = "/start",  method = RequestMethod.POST)
	public @ResponseBody JsonResponse startProcessHandler(@RequestBody JsonRequest request){
	JsonResponse response = new JsonResponse();
	response.setAccess(authenticationService.checkAuthentication(request.getUser().getUserName(), request.getUser().getPassword()));
	if(response.isAccess()){
	    //dayOffService.requestDayOff(response, 5,new Date(), "Holidays", "provider");
	    dayOffService.requestDayOff(response, request.getDayOff().getNumberDays(),request.getDayOff().getStartDate(), request.getDayOff().getMessage(),request.getUser().getUserName());
	}
	return response;
    }
    
    @RequestMapping(value = "/response",  method = RequestMethod.POST)
	public @ResponseBody JsonResponse responseDayOffProcessHandler(@RequestBody JsonRequest request){
	JsonResponse response = new JsonResponse();
	response.setAccess(authenticationService.checkAuthentication(request.getUser().getUserName(), request.getUser().getPassword()));
	if(response.isAccess()){
	    //dayOffService.acceptDayOff(response, "We have a lot of work", false, request.getTask().getId());
	    dayOffService.responseDayOff(response, request.getDayOff().getMessage(),request.getDayOff().isAccept(),request.getUser().getUserName(), request.getTask().getId());
	}
	return response;
    }
    
    @RequestMapping(value = "/adjust",  method = RequestMethod.POST)
   	public @ResponseBody JsonResponse adjustDayOffProcessHandler(@RequestBody JsonRequest request){
   	JsonResponse response = new JsonResponse();
   	response.setAccess(authenticationService.checkAuthentication(request.getUser().getUserName(), request.getUser().getPassword()));
   	if(response.isAccess()){
   	    //dayOffService.repeatRequest(response, 5,new Date(), "Holidays","provider");
   	    dayOffService.repeatRequest(response, request.getDayOff().getNumberDays(),request.getDayOff().getStartDate(), request.getDayOff().getMessage(),request.getDayOff().isRepeatRequest(), request.getUser().getUserName(), request.getTask().getId());
   	}
   	return response;
    
    }
}
