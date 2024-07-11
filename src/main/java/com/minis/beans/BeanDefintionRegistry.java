package com.minis.beans;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
public interface BeanDefintionRegistry {
   void registerBeanDefintion(String name,BeanDefinition bd);
   void removeBeanDefintion(String name);

   BeanDefinition getBeanDefiniton(String name);
   boolean containsBeanDefintion(String name);
}
