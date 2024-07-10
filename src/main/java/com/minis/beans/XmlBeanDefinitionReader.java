package com.minis.beans;

import org.dom4j.Element;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 */
public class XmlBeanDefinitionReader {
    BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinition(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String id = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(id, beanClassName);
            this.beanFactory.registerBeanDefintion(beanDefinition);
        }
    }
}