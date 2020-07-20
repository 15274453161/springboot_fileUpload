package com.qgh.spring_mvc.common.util;

/**
 * @author 秦光泓
 * @title:Json工具类
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/1013:19
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 *
 String转Object以及Object转Json字符串的需求，
 或者JSONArray转Lsit以及List转JSONArray的需求，所以抽时间封装一个常用Json工具类。
 */


public class JsonHelpers {
    private static final Logger logger = LoggerFactory.getLogger(JsonHelpers.class);
    /**
     * 需要第三方依赖---->jackson-mapper-asl
     */
    private static ObjectMapper objectMapper = new ObjectMapper();
   /* static {
        // 对象字段全部列入
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);

        // 取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 忽略空bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        // 统一日期格式yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 忽略在json字符串中存在,但是在java对象中不存在对应属性的情况
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }*/


    public ObjectMapper getInstance() {
        return objectMapper;
    }

    /***
     * JsonString转JavaBean
     * @param jsonString  json字符串
     * @param clazz 被转换对象class
     * @return java.lang.Object
     * @date 2020/7/10  13:25
     */
    public static <T> T jsonToJavaBean(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) jsonString : objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            logger.error("Parse JsonString to JavaBean error", e);
            return null;
        }
    }

    /***
     * javaBean、集合、数组 转json
     * @param obj
     * @return java.lang.String
     * @date 2020/7/10  13:33
     */

    public static String javaBeanToJson(Object obj) {
        String json = null;
        if (obj == null) {
            return null;
        }
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("Parse object to JsonString error", e.getMessage());
        }
        return json;
    }

    /***
     * javaBean、集合、数组 转json并格式化美化
     * @param obj
     * @return java.lang.String
     * @date 2020/7/10  13:33
     */

    public static String javaBeanToJsonPretty(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("Parse object to JsonString error", e.getMessage());
            return null;
        }
    }

    /**
     * 根据JSONArray String获取到List
     * (json数组的字符串)转换为list
     *
     * @param <T>
     * @param <T>
     * @param jArrayStr
     * @return
     */
    public static <T> List<T> getListByJSONArray(Class<T> class1, String jArrayStr) {
        List<T> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(jArrayStr);
        if (jsonArray == null || jsonArray.isEmpty()) {
            return list;//nerver return null
        }
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            T t = JSONObject.toJavaObject(jsonObject, class1);
            list.add(t);
        }
        return list;
    }

    /**
     * 根据List获取到对应的JSONArray
     *
     * @param list
     * @return
     */
    public static JSONArray getJSONArrayByList(List<?> list) {
        JSONArray jsonArray = new JSONArray();
        if (list == null || list.isEmpty()) {
            return jsonArray;//nerver return null
        }
        for (Object object : list) {
            jsonArray.add(object);
        }
        return jsonArray;
    }

    /**
     * JsonString转collection 用于转为集合对象
     *
     * @param jsonString      json字符串
     * @param collectionClass 被转集合class
     * @param elementClasses  被转集合中对象类型class
     * @param <T>
     * @return
     */
    public static <T> T jsonStringToCollection(String jsonString, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            logger.error("Parse String to Collection error", e);
            return null;
        }
    }

}
