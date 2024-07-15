package com.minis.beans.factory.support;

import com.minis.beans.*;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.config.ConstructorArgumentValue;
import com.minis.beans.factory.config.ConstructorArgumentValues;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/14
 */
@Slf4j
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory, BeanDefintionRegistry {
    public Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private List<String> beanDefintionsNames = new ArrayList<>();
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);

    public AbstractBeanFactory() {
    }

    public void refresh() throws BeanException {
        for (String beanName : beanDefintionsNames) {
            try {
                getBean(beanName);
            } catch (Exception e) {

            }
        }
    }

    public Object getBean(String name) throws BeanException {
        Object singleton = this.getSingleton(name);
        if (singleton == null) {
            singleton = this.earlySingletonObjects.get(name);
            if (singleton == null) {
                log.info("get bean {} 为 null", name);
                BeanDefinition bd = beanDefinitionMap.get(name);
                singleton = createBean(bd);
                this.registerBean(name, singleton);
                //TODO postprocessorBefore
                singleton =    applyBeanPostProcessorBeforeInitialization(singleton,name);
                //TODO initMehtod
                if(bd.getInitMethodName()!=null && !bd.getInitMethodName().equals("")){
                    invokeInitMEthod(bd,singleton);
                }
                //TODO postprocessorAfter
                singleton =  applyBeanPostProcessorAfterInitialization(singleton,name);

            }


        }
        log.info("获取到的bean为：{}", singleton);
        return singleton;
    }

    private void invokeInitMEthod(BeanDefinition beanDefinition, Object obj) {
        Class<? extends BeanDefinition> aClass = beanDefinition.getClass();
        Method method = null;
        try {
            aClass.getMethod(beanDefinition.getInitMethodName());
            method.invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object createBean(BeanDefinition beanDefinition) {
        Class<?> clz = null;
        log.info("创建毛胚bean");
        Object obj = doCreateBean(beanDefinition);
        this.earlySingletonObjects.put(beanDefinition.getId(), obj);
        try {
            clz = Class.forName(beanDefinition.getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        populateBean(beanDefinition, clz, obj);


        return obj;
    }
    private void populateBean(BeanDefinition bd, Class<?> clz, Object obj){
        handleProperties(bd, clz, obj);

    }

    private Object doCreateBean(BeanDefinition bd) {
        Class<?> clz = null;
        Object obj = null;
        Constructor<?> con = null;
        try {
            clz = Class.forName(bd.getClassName());
            ConstructorArgumentValues constructorArgumentValues = bd.getConstructorArgumentValues();
            Class<?>[] paramTypes = new Class<?>[constructorArgumentValues.getArgumentCount()];
            Object[] paramValues = new Object[constructorArgumentValues.getArgumentCount()];
            if (!constructorArgumentValues.isEmpty()) {
                for (int i = 0; i < constructorArgumentValues.getArgumentCount(); i++) {
                    ConstructorArgumentValue argumentValue =
                            constructorArgumentValues.getIndexedArgumentValues(i);
                    if ("String"
                            .equals(argumentValue.getType()) ||
                            "java.lang.String"
                                    .equals(argumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    } else if ("Integer"
                            .equals(argumentValue.getType()) || "java.lang.Integer"
                            .equals(argumentValue.getType())) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] =
                                Integer.valueOf((String) argumentValue.getValue());
                    } else if ("int"
                            .equals(argumentValue.getType())) {
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String)
                                argumentValue.getValue());
                    } else { //默认为string
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }
                }
                con = clz.getConstructor(paramTypes);
                obj = con.newInstance(paramValues);
            } else {
                obj = clz.newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(bd.getId() + "bean created   " + bd.getClassName() + ":" + obj.toString());
        return obj;
    }

    private void handleProperties(BeanDefinition bd, Class<?> clz, Object obj) {
        log.info("处理bean属性  id ：{}", bd.getId());
        PropertyValues propertyValues = bd.getPropertyValues();
        //如果有属性
        if (!propertyValues.isEmpty()) {
            for (int i = 0; i < propertyValues.size(); i++) {
//对每一个属性，分数据类型分别处理
                PropertyValue propertyValue =
                        propertyValues.getPropertyValueList().get(i);
                String pType = propertyValue.getType();
                String pName = propertyValue.getName();
                Object pValue = propertyValue.getVaule();
                boolean ref = propertyValue.isRef();
                //依赖注入
                Class<?>[] paramTypes = new Class<?>[1];
                Object[] paramValues = new Object[1];
                if (!ref) {
                    if ("String"
                            .equals(pType) || "java.lang.String"
                            .equals(pType)) {
                        paramTypes[0] = String.class;
                    } else if ("Integer"
                            .equals(pType) ||
                            "java.lang.Integer"
                                    .equals(pType)) {
                        paramTypes[0] = Integer.class;
                    } else if ("int"
                            .equals(pType)) {
                        paramTypes[0] = int.class;
                    } else { // 默认为string
                        paramTypes[0] = String.class;
                    }
                    paramValues[0] = pValue;
                    String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);
                    Method method = null;
                    try {
                        method = clz.getMethod(methodName, paramTypes);
                        method.invoke(obj, paramValues);
                    } catch (Exception e) {

                    }

                } else {
                    try {
                        //创建引用
                        log.info("获取到的引用类型和值为 {}  {}", paramTypes, paramValues);
                        paramTypes[0] = Class.forName(pType);
                        paramValues[0] = getBean((String) pValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        //注入依赖yu
                        String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);
                        Method method = null;

                        method = clz.getMethod(methodName, paramTypes);
                        method.invoke(obj, paramValues);
                    } catch (Exception e) {

                    }
                }
            }
        }


    }
    public abstract   Object applyBeanPostProcessorBeforeInitialization(Object existBean,String beanName) throws BeanException;
    public abstract  Object applyBeanPostProcessorAfterInitialization(Object existBean,String beanName) throws BeanException;


    @Override
    public void registerBeanDefintion(String name, BeanDefinition bd) {
        this.beanDefinitionMap.put(name, bd);
        this.beanDefintionsNames.add(name);
        if (!bd.isLazyInit()) {
            log.info("获取bean name：{}", name);
            try {
                getBean(name);
            } catch (BeanException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void removeBeanDefintion(String name) {
        this.beanNames.remove(name);
        this.beanDefinitionMap.remove(name);
        this.singletons.remove(name);
    }

    @Override
    public BeanDefinition getBeanDefiniton(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefintion(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }


    @Override
    public void registerBean(String name, Object object) {
        this.singletons.put(name, object);
    }

    @Override
    public Boolean containsBean(String name) {
        return this.singletons.containsKey(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return beanDefinitionMap.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return beanDefinitionMap.get(name).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return beanDefinitionMap.get(name).getClass();
    }
}
