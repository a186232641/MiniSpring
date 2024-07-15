package com.minis.core.env;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/15
 */
public interface PropertyResolver {
   boolean containsProperty(String key);
   String getProperty(String key);
   String getProperty(String key,String defaultValue);
   <T> T getProperty(String key,Class<T> type);
   <T> T getProperty(String key,Class<T> type,T defaultValue);
   <T> Class<T> getPropertyAsClass(String key,Class<T> targetType);
   String getRequiredProperty(String key);
   <T> T getRequiredProperty(String key,Class<T> targetType);
   String resolvePlaceholders(String text);
   String resolveRequiredPlaceholders(String text);

}
