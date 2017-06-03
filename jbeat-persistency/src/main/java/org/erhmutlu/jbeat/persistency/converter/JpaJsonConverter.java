package org.erhmutlu.jbeat.persistency.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by erhmutlu on 26/05/17.
 */
public class JpaJsonConverter implements AttributeConverter<Map, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    @Override
    public Map convertToEntityAttribute(String dbData) {
        try {
            return (Map)objectMapper.readValue(dbData, Object.class);
        } catch (IOException ex) {
            return null;
        }
    }

}
