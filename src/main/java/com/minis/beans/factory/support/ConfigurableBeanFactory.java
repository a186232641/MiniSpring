package com.minis.beans.factory.support;

import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.config.BeanPostProcessor;
import com.minis.beans.factory.config.SingletonBeanRegistry;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/15
 */

/**
 * 维护bean之间的依赖关系
 */
public interface ConfigurableBeanFactory  extends BeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON="singleton";
    String SCOPE_PROTOTYPE="prototype";
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    int getBeanPostProcessorCount();

    void registerDependentBean(String name,String dependentBeanName);
    String[] getDependentBeans(String beanName);
    String[] getDependenciesForBean(String beanName);
}
