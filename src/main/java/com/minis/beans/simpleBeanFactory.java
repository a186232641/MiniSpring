package com.minis.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 * 实现一个bean工厂
 * 保留bean
 */
public class simpleBeanFactory implements BeanFactory{
    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private List<String> beanNames = new ArrayList<>();
    private Map<String,Object> singletons = new HashMap<>();

    public simpleBeanFactory() {
    }

    @Override
    public Object getBean(String name) throws BeansException {
        Object singleton = singletons.get(name);
        if(singleton ==null){
            int i = beanNames.indexOf(name);
            if(i == -1){
                throw new BeansException();
            }else {
                BeanDefinition beanDefinition = beanDefinitions.get(i);

                    try {
                        singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                    singletons.put(name,singleton);
                    }
                     catch (Exception e) {
                        throw new RuntimeException(e);
                    }

            }

        }
        return singleton;
    }

    @Override
    public void registerBeanDefintion(BeanDefinition beanDefinition) {
    this.beanDefinitions.add(beanDefinition);
    this.beanNames.add(beanDefinition.getId());
    }
}
