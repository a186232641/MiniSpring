package com.minis.test;

import com.minis.beans.BeansException;
import com.minis.context.ClassPathXmlApplicationContext;
import com.minis.core.ClassPathXmlResource;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/14
 */
public class Test1 {
   public static void main(String[] args) throws ClassNotFoundException, BeansException {
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
      BaseBaseService bean =(BaseBaseService) context.getBean("basebaseservice");
      AServiceImpl AServiceImpl = (AServiceImpl)context.getBean("aservice");
      System.out.println(AServiceImpl);
      System.out.println(bean.getAs());


   }
}
