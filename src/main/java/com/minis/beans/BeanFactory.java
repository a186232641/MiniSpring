package com.minis.beans;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 */
public interface BeanFactory {
   Object getBean(String name) throws BeansException, ClassNotFoundException;
   void  registerBeanDefintion(BeanDefinition beanDefinition);
}
