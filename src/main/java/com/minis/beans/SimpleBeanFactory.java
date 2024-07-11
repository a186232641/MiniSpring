package com.minis.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 * 实现一个bean工厂
 * 保留bean
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);
    private List<String> beanDefintionsNames = new ArrayList<>();

    public SimpleBeanFactory() {
    }

    @Override
    public Object getBean(String name) throws BeansException {
        Object singleton = singletons.get(name);
        if (singleton == null) {
            BeanDefinition beanDefinition = beanDefinitions.get(name);

            if (beanDefinition==null) {
                throw new BeansException("no bean");
            }
                try {
                    singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                    singletons.put(name, singleton);
                } catch (Exception e){
                    throw new BeansException("bean create fail");
                }

            this.registerBean(name,singleton);
        }
        return singleton;
    }

    @Override
    public void registerBean(String name, Object object) {
        this.registerSingleton(name,object);
    }


    public void registerBeanDefinitions(String name,BeanDefinition beanDefinition) {
        this.beanDefinitions.put(name,beanDefinition);
        this.beanNames.add(name);
        if(!beanDefinition.isLazyInit()){
            try {
                getBean(name);
            } catch (BeansException e) {

            }
        }
    }

    @Override
    public Boolean containsBean(String name) {
        return containsSingleton(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return beanDefinitions.get(name).getScope().equals("singleton");
    }
    public void removeBeanDefiniton(String name){
        this.beanNames.remove(name);
        this.beanDefinitions.remove(name);
        this.singletons.remove(name);
    }
    public boolean containsBeanDefinition(String name){
        return this.beanDefinitions.containsKey(name);
    }
    public BeanDefinition getBeanDefintion(String name){
        return this.beanDefinitions.get(name);
    }
    @Override
    public boolean isPrototype(String name) {
        return this.beanDefinitions.get(name).getScope().equals("prototype");
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanDefinitions.get(name).getClass();
    }

}
