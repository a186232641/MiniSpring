package com.minis.beans.factory.support;

import com.minis.beans.BeanException;
import com.minis.beans.factory.config.AutowiredAnnotationBeanPostProcessor;
import com.minis.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/14
 */
public abstract class AbstractAutowiredCapableBeanFactory extends AbstractFactory implements AutowireCapableBeanFactory{
   private final List<BeanPostProcessor> processorList = new ArrayList<>();

   @Override
   public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
      this.processorList.add(beanPostProcessor);
   }

   @Override
   public int getBeanPostProcessorCount() {
      return this.processorList.size();
   }





   public void addBeanPostProcessor(AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor){
      this.processorList.remove(autowiredAnnotationBeanPostProcessor);
      this.processorList.add(autowiredAnnotationBeanPostProcessor);

   }

   public  int getBeanpostProcessorsCount(){
      return this.processorList.size();
   }

   @Override
   public Object applyBeanPostProcessorBeforeInitialization(Object existBean, String beanName) throws BeanException, ClassNotFoundException {
      Object result = existBean;
      for (BeanPostProcessor beanPostProcessor : processorList) {

        existBean =  beanPostProcessor.postProcessBeforeInitialization(existBean,beanName);
         if(result == null){
            return result;
         }
      }
      return  result;

   }

   @Override
   public Object applyBeanPostProcessorAfterInitialization(Object existBean, String beanName) throws BeanException {
      Object result = existBean;
      for (BeanPostProcessor beanPostProcessor : processorList) {

         result =  beanPostProcessor.postProcessAfterInitialization(existBean,beanName);
         if(result == null){
            return result;
         }
      }
      return  result;
   }

}
