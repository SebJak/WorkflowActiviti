package org.workflow.model.json;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonDeserialize
public class JsonResponse implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6309696450951856194L;

    private boolean access;
    
    private boolean success;
    
    private JsonUser user;
    
    private String processId;
    
    private List<JsonTask> userTasks;
    
    private List<JsonTask> groupTasks;
    
    private List<JsonTask> tasks;
    
    private List<JsonUser> users;
    
    private JsonTask task;
    
    

    public List<JsonUser> getUsers() {
        return users;
    }

    public void setUsers(List<JsonUser> users) {
        this.users = users;
    }

    public List<JsonTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<JsonTask> tasks) {
        this.tasks = tasks;
    }

    public JsonTask getTask() {
        return task;
    }

    public void setTask(JsonTask task) {
        this.task = task;
    }

    public List<JsonTask> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<JsonTask> userTasks) {
        this.userTasks = userTasks;
    }

    public List<JsonTask> getGroupTasks() {
        return groupTasks;
    }

    public void setGroupTasks(List<JsonTask> groupTasks) {
        this.groupTasks = groupTasks;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public JsonUser getUser() {
        return user;
    }

    public void setUser(JsonUser user) {
        this.user = user;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
