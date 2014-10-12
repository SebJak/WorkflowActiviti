package org.workflow.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.workflow.engine.api.BugReportingService;
import org.workflow.engine.api.UserService;
import org.workflow.model.json.JsonResponse;

@Controller
@RequestMapping("/")
public class BaseController {
	
	@Autowired
	private BugReportingService bugReporting;
 
	 @Autowired
	    private UserService userService;
	 
//	@Autowired
//	private RuntimeService runtimeService;
//	
	private JsonResponse response;
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		return "index";
 
	}
 
	@RequestMapping(value="/welcome/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {
		System.out.println("Dziala1 " + name);
		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - " + name);
		endIssue();
		return "index";
 
	}
	
	@RequestMapping(value="/deploy", method = RequestMethod.GET)
	public String deploy(ModelMap model) {		
		model.addAttribute("message", "Deploy - ");
		deployProcess();
		return "index";
 
	}
	
	@RequestMapping(value="/startProcess", method = RequestMethod.GET)
	public String startProcess(ModelMap model) {		
		model.addAttribute("message", "Start process - ");
		startProcess();
		return "index";
 
	}

	@RequestMapping(value="/updateBug", method = RequestMethod.GET)
	public String updateBug(ModelMap model) {		
		model.addAttribute("message", "updateBug - ");
		updateBug();
		return "index";
 
	}
	
	@RequestMapping(value="/acceptBug", method = RequestMethod.GET)
	public String acceptBug(ModelMap model) {		
		model.addAttribute("message", "Accept bug - ");
		acceptBug();
		return "index";
 
	}
	
	@RequestMapping(value="/endIssue", method = RequestMethod.GET)
	public String endIssue(ModelMap model) {		
		model.addAttribute("message", "End issue - ");
		endIssue();
		return "index";
 
	}
	
	@RequestMapping(value="/suspend", method = RequestMethod.GET)
	public String suspend(ModelMap model) {		
		model.addAttribute("message", "Suspend process - ");
		suspendProcess();
		return "index";
 
	}
	
	public void startProcess(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("projekt", "CCS - Catalog Caloud Service");
		variables.put("version", 1.1);
		variables.put("summary", "the problem with login user");
		//bugReporting.startProcess(response,variables, "bugReport");
	}
	
	public void updateBug(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("projekt", "CCS - Catalog Caloud Service");
		variables.put("version", 1.1);
		variables.put("summary", "the problem with login user");
		variables.put("targetVersion", 1.2);
		variables.put("priority", "Blocked");
		//bugReporting.completeTask(response,variables, "Update bug report","manager");
	}
	
	public void acceptBug(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("projekt", "CCS - Catalog Caloud Service");
		variables.put("version", 1.1);
		variables.put("summary", "the problem with login user");
		variables.put("targetVersion", 1.2);
		variables.put("priority", "Blocked");
		variables.put("employee", "employee");
		//bugReporting.completeTask(response,variables, "Update bug report", "employee");
	}
	
	public void endIssue(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("projekt", "CCS - Catalog Caloud Service");
		variables.put("version", 1.1);
		variables.put("summary", "the problem with login user");
		variables.put("targetVersion", 1.2);
		variables.put("priority", "Blocked");
		variables.put("employee", "employee");
		variables.put("comments", "Issue closed. Bug has fixed");
		//bugReporting.completeTask(response,variables, "Update bug report", "employee");
	}
	
	public void deployProcess(){
		//bugReporting.deploy(response,"BugReporting.bpmn20.xml");
	}
	
	public void suspendProcess(){
		bugReporting.suspendProcess(response);
	}
 
}