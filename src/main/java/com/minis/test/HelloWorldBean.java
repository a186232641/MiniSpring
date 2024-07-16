package com.minis.test;

import com.minis.web.RequestMapping;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/16
 */
public class HelloWorldBean {
   public String doGet(){
      return "hello world";
   }
   public String doPost(){
      return "hello world";
   }
   @RequestMapping("/test")
   public String doTest(){
      return "hello world test";
   }
}
