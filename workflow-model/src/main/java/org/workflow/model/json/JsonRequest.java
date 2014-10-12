package org.workflow.model.json;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonAutoDetect
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonDeserialize
public class JsonRequest {
    
    private JsonUser user;
    
    private Map<String, Object> variables;
    
    private JsonDayOff dayOff;
    
    private JsonTask task;
    
    private JsonBug bug;
    
    public JsonBug getBug() {
        return bug;
    }

    public void setBug(JsonBug bug) {
        this.bug = bug;
    }

    public JsonTask getTask() {
        return task;
    }

    public void setTask(JsonTask task) {
        this.task = task;
    }

    public JsonDayOff getDayOff() {
        return dayOff;
    }

    public void setDayOff(JsonDayOff dayOff) {
        this.dayOff = dayOff;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public JsonUser getUser() {
        return user;
    }

    public void setUser(JsonUser user) {
        this.user = user;
    }
}
