package com.minis.beans.factory.support;

import com.minis.beans.BeanException;
import com.minis.beans.factory.BeanFactory;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/15
 */

/**
 * 新增自动装配BeanFactory
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    int AUTOWIRE_NO = 0;
    int AUTOWIRE_BY_NAME = 1;
    int AUTOWIRE_BY_TYPE = 2;

    Object applyBeanPostProcessorBeforeInitialization(Object existBean, String beanName) throws BeanException, ClassNotFoundException;

    Object applyBeanPostProcessorAfterInitialization(Object existBean, String beanName) throws BeanException;
}
