package com.minis.web;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/16
 */
public class MappingValue {
   String url;
   String clz;
   String method;

   public MappingValue() {


   }

   public MappingValue(String url, String clz, String method) {
      this.url = url;
      this.clz = clz;
      this.method = method;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getClz() {
      return clz;
   }

   public void setClz(String clz) {
      this.clz = clz;
   }

   public String getMethod() {
      return method;
   }

   public void setMethod(String method) {
      this.method = method;
   }
}
