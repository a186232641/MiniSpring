package com.minis.beans;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/10
 */
public class ClassPathXmlResource implements Resource{
   Document document;
   Element rootElement;
   Iterator<Element> elementIterator;

   public ClassPathXmlResource(String fileName) {
      SAXReader saxReader = new SAXReader();
      try {
         URL resource = this.getClass().getClassLoader().getResource(fileName);
         this.document = saxReader.read(resource);
         this.rootElement = this.document.getRootElement();
          this.elementIterator =  this.rootElement.elements().iterator();
      } catch (DocumentException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public boolean hasNext() {
      return false;
   }

   @Override
   public Object next() {
      return this.elementIterator.next();
   }
}
