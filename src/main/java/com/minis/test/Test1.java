package com.minis.test;

import com.minis.beans.BeanException;
import com.minis.context.ClassPathXmlApplicationContent;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/14
 */
public class Test1 {
   public static void main(String[] args) throws ClassNotFoundException, BeanException {
      ClassPathXmlApplicationContent classPathXmlApplicationContent = new ClassPathXmlApplicationContent("beans.xml");
      BaseBaseService bean =(BaseBaseService) classPathXmlApplicationContent.getBean("bbs");
      BaseService baseService = (BaseService)classPathXmlApplicationContent.getBean("baseservice");
      System.out.println(bean);
      System.out.println(baseService.getBbs());


   }
}
