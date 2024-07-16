package com.minis.web;

import com.minis.core.Resource;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/16
 */
public class XmlConfigReader {
   public XmlConfigReader() {
   }
   public Map<String,MappingValue> loadConfig(Resource resource){
      HashMap<String, MappingValue> result = new HashMap<>();
      while (resource.hasNext()){
         Element element = (Element) resource.next();
         String beanID = element.attributeValue("id");
         String beanClassName = element.attributeValue("class");
         String beanMethod = element.attributeValue("value");
         result.put(beanID,new MappingValue(beanID,beanClassName,beanMethod));
      }
      return  result;

   }
}
