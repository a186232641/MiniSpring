package com.minis.beans.factory.config;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
@NoArgsConstructor
@Data
public class ConstruvtorArgumentValue {
   private String type;
   private Object value;
   private String name;

   public ConstruvtorArgumentValue(Object value, String name) {
      this.value = value;
      this.name = name;
   }

   public ConstruvtorArgumentValue(String type, Object value, String name) {
      this.type = type;
      this.value = value;
      this.name = name;
   }
}
