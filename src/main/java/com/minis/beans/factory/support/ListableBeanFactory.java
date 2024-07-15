package com.minis.beans.factory.support;

import com.minis.beans.BeanException;
import com.minis.beans.factory.BeanFactory;

import java.util.Map;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/15
 */
public interface ListableBeanFactory extends BeanFactory {
   boolean containsBeanDefinition(String beanName);
   int getBeanDefinitionCount();
   String[] getBeanDefinitionNames();
   String[] getBeanNamesForType(Class<?> type);
   <T> Map<String,T> getBeansOfType(Class type) throws BeanException;
}
