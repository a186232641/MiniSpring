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
   private Object value;
   private String name;
   private String type;

   public ArgumentValue(Object value, String type) {
      this.value = value;
      this.type = type;
   }

   public ArgumentValue(Object value, String name, String type) {
      this.value = value;
      this.name = name;
      this.type = type;
   }
}
