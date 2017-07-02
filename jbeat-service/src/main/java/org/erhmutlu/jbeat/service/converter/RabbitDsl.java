package org.erhmutlu.jbeat.service.converter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by erhmutlu on 02/07/17.
 */
public class RabbitDsl implements Serializable{

    private Map<String, Object> kwargs;
    private Date date = new Date();

    public RabbitDsl(Map<String, Object> kwargs) {
        this.kwargs = kwargs;
    }

    public Map<String, Object> getKwargs() {
        return kwargs;
    }

    @JsonSerialize(using = JBeatDateSerializer.class)
    public Date getDate() {
        return date;
    }
}
