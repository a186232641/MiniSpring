package com.minis.beans.factory;

import com.minis.beans.BeanException;
import com.minis.beans.factory.config.BeanDefinition;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 */
public interface BeanFactory {
   Object getBean(String name) throws BeanException, ClassNotFoundException;
   Boolean containsBean(String  name);
   boolean isSingleton(String name);
   boolean isPrototype(String name);
   Class<?> getType(String name);

   void registerBeanDefinition(BeanDefinition beanDefinition);
}
