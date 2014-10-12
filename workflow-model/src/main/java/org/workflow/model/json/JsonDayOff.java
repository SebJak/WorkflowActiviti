package org.workflow.model.json;

import java.util.Date;

public class JsonDayOff {

    private long numberDays;
    
    private Date startDate;
    
    private String message;
    
    private boolean accept;
    
    private boolean repeatRequest;
    
    public boolean isRepeatRequest() {
        return repeatRequest;
    }

    public void setRepeatRequest(boolean repeatRequest) {
        this.repeatRequest = repeatRequest;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public long getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(long numberDays) {
        this.numberDays = numberDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
