package com.minis.beans.factory;

import com.minis.beans.BeanException;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.support.AbstractAutowiredCapableBeanFactory;
import com.minis.beans.factory.support.ConfigurableListableBeanFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/15
 */
public class DefaultListableBeanFactory extends AbstractAutowiredCapableBeanFactory implements ConfigurableListableBeanFactory {


    @Override
    public void registerDependentBean(String name, String dependentBeanName) {

    }

    @Override
    public String[] getDependentBeans(String beanName) {
        return this.beanDefinitionMap.get(beanName).getDependsOn();
    }

    @Override
    public String[] getDependenciesForBean(String beanName) {
        return this.beanDefinitionMap.get(beanName).getDependsOn();
    }



    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }





    @Override
    public String[] getBeanDefinitionNames() {
        return (String[]) this.beanNames.toArray();
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        List<String> result = new ArrayList<>();
        for (String beanName : beanNames) {
            boolean matchFound = false;
            BeanDefinition mbd = this.getBeanDefiniton(beanName);
            Class<?> classToMatch = mbd.getClass();
            if(type.isAssignableFrom(classToMatch)){
                matchFound = true;
            }
            if(matchFound){
                result.add(beanName);
            }
        }
        return  (String[]) result.toArray();
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class type) throws BeanException, ClassNotFoundException {
        String[] beanNames = getBeanNamesForType(type);
        Map<String,T> result = new LinkedHashMap<>(beanNames.length);
        for (String beanName : beanNames) {
            Object bean = getBean(beanName);
            result.put(beanName,(T)bean);
        }
        return result;
    }


    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {

    }
}
