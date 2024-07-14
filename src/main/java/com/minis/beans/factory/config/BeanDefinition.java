package com.minis.beans.factory.config;

import com.minis.beans.ArgumentValues;
import com.minis.beans.PropertyValues;
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
   private boolean lazyInit = true;
   private String[] dependsOn;
   private ConstructorArgumentValues constructorArgumentValues;
   private PropertyValues propertyValues;
   private String initMethodName;
   private  volatile  Object beanClass;
   private String scope=SCOPE_SINGLETON;


   public BeanDefinition(String id, String className) {
      this.id = id;
      this.className = className;
   }
   public boolean isPrototype(){
      return SCOPE_PROTOTYPE.equals(scope);
   }
   public boolean isSingleton(){
      return SCOPE_SINGLETON.equals(scope);
   }

}
