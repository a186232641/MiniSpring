package com.minis.event;

import java.util.EventListener;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/15
 */
public class ApplicationListener implements EventListener {
   void onApplicationEvent(ApplicationEvent event){
      System.out.println(event.toString());
   }
}
