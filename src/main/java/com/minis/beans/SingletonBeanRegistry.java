package com.minis.beans;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
public interface SingletonBeanRegistry {
   void registerSingleton(String name,Object obj);
   Object getSingleton(String beanName);
   boolean containsSingleton(String beanName );
   String[] getSingletonName();
}
