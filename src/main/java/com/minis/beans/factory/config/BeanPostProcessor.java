package com.minis.beans.factory.config;

import com.minis.beans.BeanException;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/14
 */
public interface BeanPostProcessor {
   Object postProcessBeforeInitialization(Object bean,String beanName) throws BeanException, ClassNotFoundException;
   Object postProcessAfterInitialization(Object bean,String beanName)throws BeanException;

}
