package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/14
 */
@Slf4j
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {
    private AutowiredCapableBeanFactory beanFactory;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
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
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public AutowiredCapableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(AutowiredCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
