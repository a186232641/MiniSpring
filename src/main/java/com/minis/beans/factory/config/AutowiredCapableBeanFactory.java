package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.config.AutowiredAnnotationBeanPostProcessor;
import com.minis.beans.factory.support.AbstractBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/14
 */
public class AutowiredCapableBeanFactory extends AbstractBeanFactory {
   private final List<AutowiredAnnotationBeanPostProcessor> beanPostProcessors = new ArrayList<>();




   public void addBeanPostProcessor(AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor){
      this.beanPostProcessors.remove(autowiredAnnotationBeanPostProcessor);
      this.beanPostProcessors.add(autowiredAnnotationBeanPostProcessor);

   }
   public List<AutowiredAnnotationBeanPostProcessor> getBeanPostProcessors(){
      return this.beanPostProcessors;
   }
   public  int getBeanpostProcessorsCount(){
      return this.beanPostProcessors.size();
   }

   @Override
   public Object applyBeanPostProcessorBeforeInitialization(Object existBean, String beanName) throws BeansException {
      Object result = existBean;
      for (AutowiredAnnotationBeanPostProcessor beanPostProcessor : beanPostProcessors) {
         beanPostProcessor.setBeanFactory(this);
         beanPostProcessor.postProcessBeforeInitialization(existBean,beanName);
         if(result == null){
            return result;
         }
      }
      return  result;

   }

   @Override
   public Object applyBeanPostProcessorAfterInitialization(Object existBean, String beanName) throws BeansException {
      Object result = existBean;
      for (AutowiredAnnotationBeanPostProcessor beanPostProcessor : beanPostProcessors) {
         beanPostProcessor.setBeanFactory(this);
         beanPostProcessor.postProcessAfterInitialization(existBean,beanName);
         if(result == null){
            return result;
         }
      }
      return  result;
   }
}
