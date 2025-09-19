package com.sigeinv.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonMapperUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    public static <T> T deserialize(String jsonString, Class<T> targetClass) {
        return OBJECT_MAPPER.readValue(jsonString, targetClass);
    }
}
