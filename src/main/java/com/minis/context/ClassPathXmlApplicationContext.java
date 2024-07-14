package com.minis.context;

import com.minis.beans.*;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
  SimpleBeanFactory beanFactory;
    public ClassPathXmlApplicationContext(String  name,boolean isRefresh) {
        //读取XML 目录 根 等信息
        Resource classPathXmlResource = new ClassPathXmlResource(name);
        //一个简单bean工厂 完成bean的注册和获取
        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();
        //将读取到的XML文件，加载成bean，并放在bean工厂中
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(simpleBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition(classPathXmlResource);
        this.beanFactory = simpleBeanFactory;
        if(isRefresh)
            this.beanFactory.refresh();
    }
    public ClassPathXmlApplicationContext(String  fileName) {
        //读取XML 目录 根 等信息
      this(fileName,true);
    }

    @Override
    public Object getBean(String name) throws BeansException, ClassNotFoundException {
        return this.beanFactory.getBean(name);
    }

    @Override
    public void registerBean(String name, Object object) {
        this.beanFactory.registerBean(name,object);
    }

    @Override
    public Boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }


}
