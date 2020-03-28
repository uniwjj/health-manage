package com.beidou.hm.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class JSONUtil {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  static {
    // 关闭json生成器在写入完成后自动关闭写入的流，由用户控制
    OBJECT_MAPPER.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

    // 忽略未知的字段，default true
    OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // 仅序列化非Null字段
    OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    // 空字符串序列化为null
    OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
  }

  /**
   * 将json字符串转换为Object对象
   *
   * @param jsonStr json字符串
   * @param clazz 要转换成的类
   * @param <T> 泛型
   * @return 转换后的结果
   */
  public static <T> T parseObject(String jsonStr, Class<T> clazz) {
    if (StringUtils.isBlank(jsonStr)) {
      return null;
    }
    try {
      return OBJECT_MAPPER.readValue(jsonStr, clazz);
    } catch (Exception e) {
      throw new RuntimeException("deserialize error", e);
    }
  }

  /**
   * 将json字符串转换为Object对象
   *
   * @param jsonStr json字符串
   * @param type 要转换成的类
   * @param <T> 泛型
   * @return 转换后的结果
   */
  public static <T> T parseObject(String jsonStr, TypeReference<T> type) {
    try {
      return OBJECT_MAPPER.readValue(jsonStr, type);
    } catch (IOException e) {
      throw new RuntimeException("deserialize error", e);
    }
  }

  /**
   * 将json字符串转换成Object数组
   *
   * @param jsonStr json字符串
   * @param clazz 要转换成的类
   * @param <T> 泛型
   * @return 转换后的结果
   */
  public static <T> List<T> parseList(String jsonStr, Class<T> clazz) {
    if (StringUtils.isBlank(jsonStr)) {
      return Lists.newArrayList();
    }
    try {
      CollectionType collectionType = OBJECT_MAPPER.getTypeFactory()
          .constructCollectionType(ArrayList.class, clazz);
      return OBJECT_MAPPER.readValue(jsonStr, collectionType);
    } catch (Exception e) {
      throw new RuntimeException("deserialize error", e);
    }
  }

  /**
   * 将json字符串转换成Object数组
   *
   * @param jsonStr json字符串
   * @return 转换后的结果
   */
  public static List<Map<String,Object>> parseList(String jsonStr) {
    if (StringUtils.isBlank(jsonStr)) {
      return Lists.newArrayList();
    }
    try {
      return parseObject(jsonStr, new TypeReference<List<Map<String,Object>>>(){});
    } catch (Exception e) {
      throw new RuntimeException("deserialize error", e);
    }
  }

  /**
   * 将一个对象转成json字符串
   *
   * @param object 要转换的对象
   * @return 转换后的字符串
   */
  public static String toJson(Object object) {
    if (null == object) {
      return null;
    }
    try {
      return OBJECT_MAPPER.writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException("serialize error", e);
    }
  }

  /**
   * 将一个json字符串转换成map对象
   *
   * @param jsonStr 要转换的json字符串
   * @param <V> value泛型
   * @return 转换后的map结果
   */
  public static <V> Map<String, V> parseMap(String jsonStr) {
    try {
      return OBJECT_MAPPER.readValue(jsonStr, new TypeReference<Map<String, V>>() {
      });
    } catch (Exception e) {
      throw new RuntimeException("deserialize error", e);
    }
  }

  /**
   * 将一个json字符串转换成map对象
   *
   * @param jsonStr 要转换的json字符串
   */
  public static <K, V> Map<K, V> parseMap(String jsonStr, Class<K> k, Class<V> v) {
    try {
      return OBJECT_MAPPER.readValue(jsonStr, new TypeReference<Map<K, V>>() {
      });
    } catch (IOException e) {
      throw new RuntimeException("deserialize error", e);
    }
  }

}
