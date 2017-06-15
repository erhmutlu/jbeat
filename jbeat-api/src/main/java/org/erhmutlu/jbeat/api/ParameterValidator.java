package org.erhmutlu.jbeat.api;

import org.erhmutlu.jbeat.api.exceptions.JBeatExceptionCodes;
import org.erhmutlu.jbeat.api.exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by erhmutlu on 15/06/17.
 */
public class ParameterValidator {

    public static <T> void validate(String name, T value) throws ValidationException {
        if (value == null) {
            throw new ValidationException(JBeatExceptionCodes.NULL_PARAMETER, name);
        }
        if (value instanceof String) {
            if (StringUtils.isBlank(StringUtils.strip((String) value))) {
                throw new ValidationException(JBeatExceptionCodes.EMPTY_PARAMETER, name);
            }
        }
    }

    //TODO typecheck
//    public static <T> void validate(String name, T value, Class clz) throws ValidationException {
//        validate(name, value);
//        if(value.getClass() != clz){
//            throw new ValidationException(JBeatExceptionCodes.WRONG_TYPE_PARAMETER, new Object[]{name, value.getClass().getSimpleName(), clz.getSimpleName()});
//        }
//    }
}
