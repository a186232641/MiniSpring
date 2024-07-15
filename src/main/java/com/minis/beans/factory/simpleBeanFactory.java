package com.minis.beans.factory;

import com.minis.beans.BeanException;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/15
 */
public class simpleBeanFactory extends DefaultListableBeanFactory {
   public simpleBeanFactory() {
   }

   @Override
   public Object applyBeanPostProcessorBeforeInitialization(Object existBean, String beanName) throws BeanException, ClassNotFoundException {
      return null;
   }

   @Override
   public Object applyBeanPostProcessorAfterInitialization(Object existBean, String beanName) throws BeanException {
      return null;
   }
}
