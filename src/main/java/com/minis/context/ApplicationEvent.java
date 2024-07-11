package com.minis.context;

import java.util.EventObject;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
public class ApplicationEvent extends EventObject {
   /**
    * Constructs a prototypical Event.
    *
    * @param source The object on which the Event initially occurred.
    * @throws IllegalArgumentException if source is null.
    */
   private static  final  long  serialVersionUID=1L;
   public ApplicationEvent(Object source) {

      super(source);
   }
}
