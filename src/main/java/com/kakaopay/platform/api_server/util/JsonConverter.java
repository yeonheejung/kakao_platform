package com.kakaopay.platform.api_server.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class JsonConverter {

    public static String objectToJson(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * String 형태의 json을 원하는 DTO로 변환하는 유틸
     * @param json
     * @param dto
     * @return
     * @throws Exception
     */
    public static Object jsonToObject(String json, Class dto) {
        try {
            Object obj = dto.newInstance();
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(json, obj.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object jsonToObject(File file, Class dto)  {
        try {
            Object obj = dto.newInstance();
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(file, obj.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
