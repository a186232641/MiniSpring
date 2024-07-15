package com.minis.beans.factory.xml;

import com.minis.beans.*;
import com.minis.beans.factory.support.AbstractAutowiredCapableBeanFactory;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.config.ConstructorArgumentValues;
import com.minis.beans.factory.config.ConstructorArgumentValue;
import com.minis.core.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 */
@Slf4j
public class XmlBeanDefinitionReader {
    AbstractAutowiredCapableBeanFactory beanFactory;

    public XmlBeanDefinitionReader(AbstractAutowiredCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinition(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
        log.info("读取的bean为 {} : {} ",beanId,beanClassName);
            //创建bean定义
            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
            //获取xml文件中property的标签
            List<Element> properties = element.elements("property");
            //获取依赖列表
            List<String> refs = new ArrayList<>();
            //获取xml文件中property的标签
            PropertyValues PVS = new PropertyValues();
            for (Element e : properties) {
                //获取属性值
                String ptype=e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                String pRef = e.attributeValue("ref");
                String PV = "";
                boolean isRef = false;
                if(pValue!=null && !pValue.equals("")){
                    isRef = false;
                    PV=pValue;
                }else if(pRef!=null && !pRef.equals("")){
                    isRef = true;
                    PV = pRef;
                    refs.add(pRef);
                }

                PVS.addPropertyValue(new PropertyValue(ptype,pName,PV,isRef));
            }
            log.info("读取的bean Property为 {} ",PVS);
            beanDefinition.setPropertyValues(PVS);
            String[] array = refs.toArray(new String[0]);
            beanDefinition.setDependsOn(array);
            //获取构造器注入
            List<Element> constructorElements = element.elements("constructor-arg");
            ConstructorArgumentValues AVS = new ConstructorArgumentValues();
            for (Element e : constructorElements) {
                String ptype=e.attributeValue("type");
                String pName = e.attributeValue("name");
                String pValue = e.attributeValue("value");
                AVS.addConstructorArgumentValue(new ConstructorArgumentValue(ptype,pValue,pName));
            }
            log.info("读取的bean 构造器为 {} ",AVS);
            beanDefinition.setConstructorArgumentValues(AVS);

            log.info("注册bean Id:{}  beanDefiniton",beanId,beanDefinition);
            this.beanFactory.registerBeanDefintion(beanId,beanDefinition);
        }
    }
}
