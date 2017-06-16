package org.erhmutlu.jbeat.starter.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by erhmutlu on 16/06/17.
 */

@ControllerAdvice
public class JBeatControllerAdvice extends ResponseEntityExceptionHandler {


    /**
     *
     * Modifies response in exception
     *
     *  Ex: {
         "exception_code": 52282003,
         "message": "PeriodicTask is already exist!",
         "status": 500
        }
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(JBeatException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        JBeatException jBeatException = (JBeatException) ex;

        HttpStatus status = getStatus(request);
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("exception_code", jBeatException.getCode());
        map.put("message", jBeatException.getMessage());
        return new ResponseEntity<>(map, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
