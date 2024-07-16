package com.minis.web;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/16
 */
public class XmlComponentHelper {
   public static List<String> getNodeValue(URL xmlPath){
      List<String> packages = new ArrayList<>();
      SAXReader saxReader = new SAXReader();
      Document document = null;
      try {
         document = saxReader.read(xmlPath);

      } catch (DocumentException e) {
        e.printStackTrace();
      }
      assert  document==null;
      Element rootElement = document.getRootElement();
      Iterator it = rootElement.elementIterator();
      while (it.hasNext()){
         Element element = (Element) it.next();
         packages.add(element.attributeValue("base-package"));
      }
      return packages;
   }
}
