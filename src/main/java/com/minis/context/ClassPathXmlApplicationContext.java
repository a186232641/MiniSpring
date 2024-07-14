package com.minis.context;

import com.minis.beans.*;
import com.minis.beans.factory.config.AutowiredCapableBeanFactory;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.config.AutowiredAnnotationBeanPostProcessor;
import com.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

import java.util.List;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    AutowiredCapableBeanFactory beanFactory;
    public ClassPathXmlApplicationContext(String  name,boolean isRefresh) throws BeansException {
        //读取XML 目录 根 等信息
        Resource classPathXmlResource = new ClassPathXmlResource(name);
        //一个简单bean工厂 完成bean的注册和获取
        AutowiredCapableBeanFactory autowiredCapableBeanFactory = new AutowiredCapableBeanFactory();
        //将读取到的XML文件，加载成bean，并放在bean工厂中
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(autowiredCapableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition(classPathXmlResource);
        this.beanFactory = autowiredCapableBeanFactory;
        if(isRefresh){
            refresh();
        }

    }
    public ClassPathXmlApplicationContext(String  fileName) throws BeansException {
        //读取XML 目录 根 等信息
      this(fileName,true);
    }
    public void refresh() throws BeansException {
        registerBeanPostProcessors(this.beanFactory);
        onRefresh();
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
    public List<AutowiredAnnotationBeanPostProcessor> getPostProcessors(){
        return this.beanFactory.getBeanPostProcessors();
    }
    private void registerBeanPostProcessors(AutowiredCapableBeanFactory autowiredCapableBeanFactory){
        beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());


    }
    private void onRefresh() throws BeansException {
        this.beanFactory.refresh();
    }


}
