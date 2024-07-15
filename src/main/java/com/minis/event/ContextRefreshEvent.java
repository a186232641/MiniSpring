package com.minis.event;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/15
 */
public class ContextRefreshEvent extends ApplicationEvent {
   private static  final  long serialVersionUID =1L;


   public ContextRefreshEvent(Object source) {
      super(source);
   }

   @Override
   public String toString() {
      return this.msg;
   }
}
