package com.minis.beans;

import lombok.Data;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
@Data
public class PropertyValue {
   private final  String name;
   private final Object vaule;

   public PropertyValue(String name, Object vaule) {
      this.name = name;
      this.vaule = vaule;
   }
}
