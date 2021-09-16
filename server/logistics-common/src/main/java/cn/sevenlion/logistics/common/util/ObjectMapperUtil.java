package cn.sevenlion.logistics.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: qimeiwen
 * @create: 2021-09-16
 */
public class ObjectMapperUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String obj2Str(Object obj) {
        String value = null;
        try {
            value = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return value;
    }
}
