package com.minis.test;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/14
 */
public class BaseBaseService {
   private AServiceImpl as;

   public AServiceImpl getAs() {
      return as;
   }

   public BaseBaseService() {
   }

   public void setAs(AServiceImpl aService) {
      this.as = aService;
   }
   public void sayHello(){
      System.out.println("base base service says hello");
   }
}
