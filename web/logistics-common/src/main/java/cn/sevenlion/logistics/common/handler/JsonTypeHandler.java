package cn.sevenlion.logistics.common.handler;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: qimeiwen
 * @create: 2021-09-11
 */
public class JsonTypeHandler<T> extends JacksonTypeHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    private Class<T> type;


    public JsonTypeHandler(Class<T> type) {
        super(type);
        this.type = type;
    }

    @Override
    protected T parse(String json) {
        T result = null;
        try {
            result = objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
