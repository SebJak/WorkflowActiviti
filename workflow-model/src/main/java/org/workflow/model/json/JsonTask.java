package org.workflow.model.json;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonDeserialize
public class JsonTask implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 5139173594981804683L;

    private String name;
    
    private String id;
    
    private String description;
    
    private String assignee;
    
    private Date createDate;
    
    private Date dueDate;
    
    private String executionId;
    
    private String owner;
    
    private String parentTaskId;
    
    private int priority;
    
    private String processDefinitionId;
    
    private String processInstanceId;
    
    private Map<String, Object> processVariables;
    
    private String taskDefinitionKey;
    
    private Map<String, Object> taskLocalVariable;
    
    private boolean suspend;
    
    public JsonTask(Task task){
	this.assignee = task.getAssignee();
	//this.category = task.get
	this.createDate = task.getCreateTime();
	this.description = task.getDescription();
	this.dueDate = task.getDueDate();
	this.executionId = task.getExecutionId();
	this.id = task.getId();
	this.name = task.getName();
	this.owner = task.getOwner();
	this.parentTaskId = task.getParentTaskId();
	this.priority = task.getPriority();
	this.processDefinitionId = task.getProcessDefinitionId();
	this.processInstanceId = task.getProcessInstanceId();
	this.processVariables = task.getProcessVariables();
	this.suspend = task.isSuspended();
	this.taskDefinitionKey = task.getTaskDefinitionKey();
	this.taskLocalVariable = task.getTaskLocalVariables();
    }
    
    public JsonTask() {
	super();
	// TODO Auto-generated constructor stub
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Map<String, Object> getProcessVariables() {
        return processVariables;
    }

    public void setProcessVariables(Map<String, Object> processVariables) {
        this.processVariables = processVariables;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionId) {
        this.taskDefinitionKey = taskDefinitionId;
    }

    public Map<String, Object> getTaskLocalVariable() {
        return taskLocalVariable;
    }

    public void setTaskLocalVariable(Map<String, Object> taskLocalVariable) {
        this.taskLocalVariable = taskLocalVariable;
    }

    public boolean isSuspend() {
        return suspend;
    }

    public void setSuspend(boolean suspend) {
        this.suspend = suspend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
