package cn.sevenlion.logistics.back.basic.web;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */

public class ConvertUtils {

    @SneakyThrows
    public static <T> T convert(Object source, Class<T> clazz) {
        //返回的对象
        T bean = (T) clazz.newInstance();
        //返回对象的字段
        Field[] beanFields = clazz.getDeclaredFields();
        Map<String, Field> beanFieldMap = Stream.of(beanFields).collect(Collectors.toMap(Field::getName, Function.identity()));

        //得到源对象的字段和方法
        Class sourceClass = source.getClass();
        Field[] sourceFields = sourceClass.getDeclaredFields();
        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            Field field = beanFieldMap.get(sourceField.getName());
            if (field != null) {
                field.setAccessible(true);
                field.set(bean, sourceField.get(source));
            }
        }
        return bean;
    }

}
