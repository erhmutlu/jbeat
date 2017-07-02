package org.erhmutlu.jbeat.service.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by erhmutlu on 02/07/17.
 */
public class JBeatDateSerializer extends JsonSerializer<Date> {

    private final static String DEFAULT_ZONE = "Europe/Istanbul";
    public final static ZoneId ZONE_UTC = ZoneId.of("UTC");
    public final static ZoneId ZONE_TR = ZoneId.of(DEFAULT_ZONE);

    private final static String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";


    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String formattedDate = format(date, pattern);
        gen.writeString(formattedDate);
    }

    /**
     * @param date
     * @param pattern
     * @return format date with turkey zone
     */
    public static String format(Date date, String pattern) {
        return format(date, pattern, ZONE_TR);
    }

    /**
     * @param date
     * @param pattern
     * @param zoneId
     * @return format date with specific zone
     */
    public static String format(Date date, String pattern, ZoneId zoneId) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(zoneId);
        ZonedDateTime zdt = ZonedDateTime.ofInstant(date.toInstant(),zoneId);
        return zdt.format(formatter);

    }

}
