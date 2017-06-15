package org.erhmutlu.jbeat.api.exceptions;

/**
 * Created by erhmutlu on 15/06/17.
 */
public class ValidationException extends JBeatException{

    public ValidationException(int code, String param) {
        super(code, new Object[]{param});
    }

    public ValidationException(int code, Object[] params) {
        super(code, params);
    }

    public ValidationException(int code) {
        super(code);
    }
}
