package com.minis.event;


/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
public interface ApplicationEventPublisher {
   void publishEvent(ApplicationEvent event);
   void addApplicationListener(ApplicationListener applicationListener);
}
