package com.minis.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
public class DefaultSingletonBeanRegistry implements  SingletonBeanRegistry{
   protected List<String> beanNames = new ArrayList<>();
   protected Map<String,Object> singletons = new ConcurrentHashMap<>(256);

   @Override
   public void registerSingleton(String name, Object obj) {
      synchronized (this.singletons){
         this.singletons.put(name,obj);
         this.beanNames.add(name);
      }
   }

   @Override
   public Object getSingleton(String beanName) {
      return singletons.get(beanName);
   }

   @Override
   public boolean containsSingleton(String beanName) {
      return singletons.containsKey(beanName);
   }


   @Override
   public String[] getSingletonName() {
      return (String[]) this.beanNames.toArray();
   }
   protected void removeSingleton(String beanName){
      synchronized (this.singletons){
         this.singletons.remove(beanName);
         this.beanNames.remove(beanName);
      }
   }
}
