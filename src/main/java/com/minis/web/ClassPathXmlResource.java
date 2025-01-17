package com.minis.web;

import com.minis.core.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.net.URL;
import java.util.Iterator;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/16
 */
public class ClassPathXmlResource  implements Resource {
   Document document;
   Element rootElement;
   Iterator<Element> elementIterator;

   public ClassPathXmlResource(URL xmlPath) {
      SAXReader saxReader = new SAXReader();
      try {
         this.document=saxReader.read(xmlPath);
         this.rootElement = document.getRootElement();
         this.elementIterator = this.rootElement.elementIterator();
      } catch (DocumentException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public boolean hasNext() {
      return this.elementIterator.hasNext();
   }

   @Override
   public Object next() {
      return this.elementIterator.next();
   }
}
