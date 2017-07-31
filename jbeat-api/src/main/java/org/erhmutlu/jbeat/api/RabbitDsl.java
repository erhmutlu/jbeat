package org.erhmutlu.jbeat.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by erhmutlu on 02/07/17.
 */
public class RabbitDsl implements Serializable{

    private Map<String, Object> kwargs;
    private String task;
    private Date date = new Date();

    public RabbitDsl(Map<String, Object> kwargs, String taskName) {
        this.kwargs = kwargs;
        this.task = taskName;
    }

    public RabbitDsl() {
    }

    public Map<String, Object> getKwargs() {
        return kwargs;
    }

//    @JsonSerialize(using = JBeatDateSerializer.class)
    public Date getDate() {
        return date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setKwargs(Map<String, Object> kwargs) {
        this.kwargs = kwargs;
    }

    public void setDate(Long date) {
        this.date = new Date(date);
    }
}
