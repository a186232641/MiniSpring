package com.minis.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BeanDefinition {
   private String id;
   private String className;
   String SCOPE_SINGLETON = "singleton";
   String SCOPE_PROTOTYPE = "prototype";
   private boolean lazyInit = false;
   private String[] dependsOn;
   private ArgumentValues argumentValues;
   private PropertyValues propertyValues;
   private String initMethodName;
   private  volatile  Object beanClass;
   private String scope=SCOPE_SINGLETON;
}
