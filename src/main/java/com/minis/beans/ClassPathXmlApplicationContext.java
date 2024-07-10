package com.minis.beans;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 */
public class ClassPathXmlApplicationContext implements BeanFactory{
    BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String  name) {
        Resource classPathXmlResource = new ClassPathXmlResource(name);
        simpleBeanFactory simpleBeanFactory = new simpleBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition(classPathXmlResource);
        this.beanFactory = simpleBeanFactory;
    }

    @Override
    public Object getBean(String name) throws BeansException, ClassNotFoundException {
        return this.beanFactory.getBean(name);
    }

    @Override
    public void registerBeanDefintion(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefintion(beanDefinition);
    }
}
