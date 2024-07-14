package com.minis.test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/12
 */
@AllArgsConstructor
@Data
public class AServiceImpl  implements AService {
   private String name;
   private int level;
   private String property1;
   private String property2;
   private  BaseService ref1;

   public BaseService getRef1() {
      return ref1;
   }

   public void setRef1(BaseService ref1) {
      this.ref1 = ref1;
   }

   public AServiceImpl() {
   }

   public AServiceImpl(String name, int level) {
      this.name = name;
      this.level = level;
      System.out.println(this.name+","+this.level);
   }

   @Override
   public void sayHello() {
      System.out.println(this.property1 + "," + this.property2);
   }
}
