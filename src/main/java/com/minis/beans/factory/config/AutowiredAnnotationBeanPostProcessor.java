package com.minis.beans.factory.config;

import com.minis.beans.BeanException;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.annotation.Autowired;
import com.minis.beans.factory.support.AbstractAutowiredCapableBeanFactory;
import com.minis.beans.factory.support.BeanFactoryPostProcessor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/14
 */
@Slf4j
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {
    private BeanFactory beanFactory;

    public AutowiredAnnotationBeanPostProcessor(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException, ClassNotFoundException {
        Object result = bean;
        Class<?> aClass = bean.getClass();
        Field[] fields = aClass.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                boolean isAutoWired = field.isAnnotationPresent(Autowired.class);
                if (isAutoWired) {
                    String fieldName = field.getName();
                    Object autoWiredObj = this.beanFactory.getBean(fieldName);
                    try {
                        field.setAccessible(true);
                        field.set(bean, autoWiredObj);
                        log.info("autowire {} for bean {}", fieldName, beanName);
                    } catch (Exception e){
                        log.error("bean字段 注入异常",e);
                    }
                }
            }
        }
        return  result;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(AbstractAutowiredCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
