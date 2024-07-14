package com.minis.beans;

import lombok.*;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
@Setter
@Data
@Getter
public class PropertyValue {
   private final  String type;
   private final  String name;
   private final Object vaule;
   private final boolean isRef;

   public PropertyValue(String type, String name, Object vaule, boolean isRef) {
      this.type = type;
      this.name = name;
      this.vaule = vaule;
      this.isRef = isRef;
   }

}
