package org.erhmutlu.jbeat.persistency.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by erhmutlu on 26/05/17.
 */
public class JpaJsonConverter implements AttributeConverter<Map, String> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception ex) {
            //TODO can be better ?
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Map convertToEntityAttribute(String dbData) {
        try {
            return (Map)objectMapper.readValue(dbData, Object.class);
        } catch (Exception ex) {
            //TODO can be better ?
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

}
