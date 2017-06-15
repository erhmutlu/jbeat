package org.erhmutlu.jbeat.api.exceptions;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by erhmutlu on 08/06/17.
 */
public class JBeatException extends Exception {

    private int code = -1;
    private Object[] params;

    public JBeatException(Throwable cause) {
        super(cause);
    }

    public JBeatException(int code) {
        this.code = code;
    }

    public JBeatException(int code, Object[] params) {
        this.code = code;
        this.params = params;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object[] getParams() {
        return params;
    }

    public String getMessage(){
        if(null == super.getMessage()){
            ResourceBundle resourceBundle = ResourceBundle.getBundle("exception_messages_en", new Locale("en"));
            Set<String> keys = resourceBundle.keySet();
            String thisKey = ""+this.getCode()+"";
            if(keys.contains(thisKey)){
                return MessageFormat.format(resourceBundle.getString(thisKey), this.getParams());
            }else {
                return super.getMessage();
            }
        }
        else {
            return super.getMessage();
        }
    }
}
