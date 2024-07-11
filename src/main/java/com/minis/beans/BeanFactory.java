package com.minis.beans;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 */
public interface BeanFactory {
   Object getBean(String name) throws BeansException, ClassNotFoundException;
   void  registerBean(String name,Object object);
   Boolean containsBean(String  name);
   boolean isSingleton(String name);
   boolean isPrototype(String name);
   Class<?> getType(String name);
}
