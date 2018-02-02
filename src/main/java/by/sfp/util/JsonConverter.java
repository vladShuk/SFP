package by.sfp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonConverter {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> String toJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonConvertingException(String.format("Incorrect converting object %s to JSON", object.getClass().getCanonicalName()));
        }
    }
}
