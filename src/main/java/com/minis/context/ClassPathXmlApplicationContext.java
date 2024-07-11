package com.minis.context;

import com.minis.beans.*;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
  SimpleBeanFactory simpleBeanFactory;

    public ClassPathXmlApplicationContext(String  name) {
        //读取XML 目录 根 等信息
        Resource classPathXmlResource = new ClassPathXmlResource(name);
        //一个简单bean工厂 完成bean的注册和获取
        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();
        this.simpleBeanFactory = simpleBeanFactory;
        //将读取到的XML文件，加载成bean，并放在bean工厂中
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(simpleBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition(classPathXmlResource);
    }

    @Override
    public Object getBean(String name) throws BeansException, ClassNotFoundException {
        return this.simpleBeanFactory.getBean(name);
    }

    @Override
    public void registerBean(String name, Object object) {
        this.simpleBeanFactory.registerBean(name,object);
    }

    @Override
    public Boolean containsBean(String name) {
        return this.simpleBeanFactory.containsBean(name);
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


    public void registerBeanDefintion(String name, BeanDefinition beanDefinition) {
       this.simpleBeanFactory.registerBeanDefinitions(name,beanDefinition);
    }
}
