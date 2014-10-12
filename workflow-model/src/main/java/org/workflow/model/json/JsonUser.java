package org.workflow.model.json;

import java.io.Serializable;
import java.util.List;

import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonDeserialize
public class JsonUser implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -3935966272918626886L;

    private String userName;
    
    private String password;
    
    private String confPassword;
    
    private String email;
    
    private String firstName;
    
    private String lastName;
    
    public JsonUser() {
	super();
    }

    public JsonUser(String userName, String password, String confPassword,
	    String email, String firstName, String lastName) {
	super();
	this.userName = userName;
	this.password = password;
	this.confPassword = confPassword;
	this.email = email;
	this.firstName = firstName;
	this.lastName = lastName;

    }

    public JsonUser(User user){
	
	this.userName = user.getId();
	this.email = user.getEmail();
	this.firstName = user.getFirstName();
	this.lastName = user.getLastName();
    
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
