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
import org.workflow.engine.api.BugReportingService;
import org.workflow.model.json.JsonRequest;
import org.workflow.model.json.JsonResponse;

@Controller
@RequestMapping(value = "/bugReporting", produces = { "application/json; charset=utf-8" })
public class BugReportingControllerJson extends ApplicationObjectSupport {

    @Autowired
    private BugReportingService bugReportingService;

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

    @RequestMapping(value = "/deploy", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse deploynHandler(@RequestBody JsonRequest request) {

	JsonResponse response = new JsonResponse();
	response.setAccess(authenticationService.checkAuthentication(request
		.getUser().getUserName(), request.getUser().getPassword()));
	if (response.isAccess()) {
	    bugReportingService.deploy(response);
	}
	return response;

    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse startProcessHandler(@RequestBody JsonRequest request) {
	JsonResponse response = new JsonResponse();
	response.setAccess(authenticationService.checkAuthentication(request
		.getUser().getUserName(), request.getUser().getPassword()));
	if (response.isAccess()) {
	    bugReportingService.startProcess(response, request.getBug().getProject(), request.getBug().getVersion(), request.getBug().getMessage(), request.getBug().getDueDate(), request.getBug().getTitle(), request.getUser().getUserName());
	}
	return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse updateProcessHandler(@RequestBody JsonRequest request) {
	JsonResponse response = new JsonResponse();
	response.setAccess(authenticationService.checkAuthentication(request
		.getUser().getUserName(), request.getUser().getPassword()));
	if (response.isAccess()) {
	    bugReportingService.updateBugReport(response, request.getBug().getProject(), request.getBug().getVersion(), request.getBug().getMessage(), request.getBug().getDueDate(), request.getBug().getTargetVersion(), request.getBug().getPriority(), request.getBug().getDeveloper(), request.getBug().getTitle(), request.getUser().getUserName(), request.getTask().getId());
	}
	return response;
    }

    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse acceptProcessHandler(@RequestBody JsonRequest request) {
	JsonResponse response = new JsonResponse();
	response.setAccess(authenticationService.checkAuthentication(request
		.getUser().getUserName(), request.getUser().getPassword()));
	if (response.isAccess()) {
	    bugReportingService.acceptBug(response, request.getBug().getProject(), request.getBug().getVersion(), request.getBug().getTargetVersion(), request.getBug().getPriority(), request.getBug().getSummary(), request.getBug().getDeveloper(), request.getBug().getTitle(), request.getBug().getEstimatedTime(), request.getUser().getUserName(), request.getTask().getId());
	}
	return response;
    }
    
    @RequestMapping(value = "/resolve", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse resolveHandler(@RequestBody JsonRequest request) {
	JsonResponse response = new JsonResponse();
	response.setAccess(authenticationService.checkAuthentication(request
		.getUser().getUserName(), request.getUser().getPassword()));
	if (response.isAccess()) {
	    bugReportingService.resolveBug(response, request.getBug().getProject(), request.getBug().getVersion(), request.getBug().getTargetVersion(), request.getBug().getPriority(), request.getBug().getSummary(), request.getBug().getDeveloper(), request.getBug().getResolution(),request.getBug().getTitle(), request.getUser().getUserName(), request.getTask().getId());
	}
	return response;
    }
    
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse closeIssueHandler(@RequestBody JsonRequest request) {
	JsonResponse response = new JsonResponse();
	response.setAccess(authenticationService.checkAuthentication(request
		.getUser().getUserName(), request.getUser().getPassword()));
	if (response.isAccess()) {
	    bugReportingService.testFixedBug(response, request.getBug().getTitle(), request.getBug().getProject(), request.getBug().getVersion(), request.getBug().getMessage(), request.getBug().isFixed(), request.getTask().getId());
	}
	return response;
    }
}
