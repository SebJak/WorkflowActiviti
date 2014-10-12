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
import org.workflow.engine.api.UserService;
import org.workflow.model.json.JsonRequest;
import org.workflow.model.json.JsonResponse;


@Controller
@RequestMapping(value = "/main", produces={"application/json; charset=utf-8"})
public class MainControllerJson extends ApplicationObjectSupport {

    @Autowired
    private AuthService authenticationService;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse defaultHandler(@RequestBody JsonRequest request,
	    HttpServletRequest requestq) {

	JsonResponse response = new JsonResponse();
	response.setAccess(true);
	return response;

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody JsonResponse firstLoginHandler(@RequestBody JsonRequest request) {
		
	JsonResponse response = new JsonResponse();
	response.setAccess(authenticationService.checkAuthentication(request.getUser().getUserName(), request.getUser().getPassword()));
	if(response.isAccess()){
	    //System.out.println("OK");
	    response = userService.getUserDetails(response, request.getUser().getUserName());
	    //System.out.println("OK");
	}
	return response;
		
	}
}
