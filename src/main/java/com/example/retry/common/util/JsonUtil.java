package com.example.retry.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-30 20:32
 */
@Slf4j
public class JsonUtil {

    /**
     * 根据 json key进行排序
     * @param str
     * @return
     * @throws JsonProcessingException
     */
    public static String sortJsonByKey(String str) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(str);
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true);
        Object obj = objectMapper.treeToValue(jsonNode, Object.class);
        String orderStr = objectMapper.writeValueAsString(obj);
        return orderStr;
    }

    public static void main(String[] args) throws JsonProcessingException {
        String jsonString = "{\"zhangsan\":10,\"lisi\":20,\"wangwu\":80}";
        String s = sortJsonByKey(jsonString);
        log.info("sorted str:{}",s);

    }
}
