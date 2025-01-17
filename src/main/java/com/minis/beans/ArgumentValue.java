package com.minis.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
@NoArgsConstructor
@Data
public class ArgumentValue {
   private String type;
   private Object value;
   private String name;

   public ArgumentValue(Object value, String name) {
      this.value = value;
      this.name = name;
   }

   public ArgumentValue(String type, Object value, String name) {
      this.type = type;
      this.value = value;
      this.name = name;
   }
}
