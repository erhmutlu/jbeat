package org.erhmutlu.jbeat.service;

import org.assertj.core.api.Assertions;
import org.erhmutlu.jbeat.api.ParameterValidator;
import org.erhmutlu.jbeat.api.exceptions.JBeatExceptionCodes;
import org.erhmutlu.jbeat.api.exceptions.ValidationException;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by erhmutlu on 15/06/17.
 */
public class ParameterValidatorTest {

    @Test
    public void testValidate() throws ValidationException {
        ParameterValidator.validate("test", "dummy");
        ParameterValidator.validate("test", 5L);
        ParameterValidator.validate("test", 5);
        ParameterValidator.validate("test", 5.5);
        ParameterValidator.validate("test", 5.5d);
        ParameterValidator.validate("test", new HashMap<>());

//        ParameterValidator.validate("test", 5.5d, Double.class);
//        ParameterValidator.validate("test", "5.5", Double.class);
    }

    @Test
    public void testValidateException() throws ValidationException {
        Assertions.assertThatThrownBy(() -> ParameterValidator.validate("test", "")).isInstanceOf(ValidationException.class).hasFieldOrPropertyWithValue("code", JBeatExceptionCodes.EMPTY_PARAMETER);
        Assertions.assertThatThrownBy(() -> ParameterValidator.validate("test", " ")).isInstanceOf(ValidationException.class).hasFieldOrPropertyWithValue("code", JBeatExceptionCodes.EMPTY_PARAMETER);;
        Assertions.assertThatThrownBy(() -> ParameterValidator.validate("test", null)).isInstanceOf(ValidationException.class).hasFieldOrPropertyWithValue("code", JBeatExceptionCodes.NULL_PARAMETER);;
    }
}
