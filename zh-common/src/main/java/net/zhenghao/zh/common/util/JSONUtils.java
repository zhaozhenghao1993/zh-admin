package net.zhenghao.zh.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/12/15 22:42
 * JSONUtils.java
 */

public class JSONUtils {

    private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);

    private JSONUtils() {
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param obj
     * @return
     */
    public static <T> String objToString(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("JSONUtils objToString exception", e);
            return null;
        }
    }

    /**
     * 有格式的
     *
     * @param obj
     * @return
     */
    public static <T> String objToStringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("JSONUtils objToStringPretty exception", e);
            return null;
        }
    }

    /**
     * 字符串转对象
     *
     * @param str
     * @param clazz
     * @return
     */
    public static <T> T stringToObj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            logger.error("JSONUtils stringToObj Class<T> exception", e);
            return null;
        }
    }

    /**
     * 字段符转List之类的集合
     *
     * @param str
     * @param typeReference
     * @return
     */
    public static <T> T stringToObj(String str, TypeReference typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            logger.error("JSONUtils stringToObj TypeReference exception", e);
            return null;
        }
    }

    /**
     * 差不多同上
     *
     * @param str
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    public static <T> T stringToObj(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            logger.error("JSONUtils stringToObj collectionClass exception", e);
            return null;
        }
    }

}
