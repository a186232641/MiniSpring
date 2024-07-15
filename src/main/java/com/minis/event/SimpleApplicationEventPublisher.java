package com.minis.event;



import java.util.ArrayList;
import java.util.List;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/15
 */
public class SimpleApplicationEventPublisher implements ApplicationEventPublisher{
   List<ApplicationListener> listeners = new ArrayList<>();
   @Override
   public void publishEvent(ApplicationEvent event) {
      for (ApplicationListener listener : listeners) {
         listener.onApplicationEvent(event);
      }
   }

   @Override
   public void addApplicationListener(ApplicationListener applicationListener) {
      this.listeners.add(applicationListener);
   }
}
