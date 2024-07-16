package com.minis.web;

import com.minis.core.Resource;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/16
 */
public class DispatcherServlet extends HttpServlet {
    private Map<String,MappingValue> mappingValues;
    private Map<String,Class<?>> mappingclz = new HashMap<>();
    private Map<String,Object> mappingObjs = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String scontextConfigLocation = config.getInitParameter("contextConfigLocation");
        URL xmlPath = null;
        xmlPath =   this.getClass().getClassLoader().getResource(scontextConfigLocation);
        Resource rs = new ClassPathXmlResource(xmlPath);
        XmlConfigReader reader = new XmlConfigReader();
        mappingValues = reader.loadConfig(rs);
       Refresh();

    }
    protected  void Refresh(){
        for (Map.Entry<String, MappingValue> entry : mappingValues.entrySet()) {
            String id =entry.getKey();
            String className=entry.getValue().getClz();
            Object obj = null;
            Class<?> clz = null;
            try {
                clz = Class.forName(className);
                obj = clz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            mappingclz.put(id,clz);
            mappingObjs.put(id,obj);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sPath = req.getServletPath();
        if(this.mappingValues.get(sPath) == null){
            return;
        }
        Class<?> clz = this.mappingclz.get(sPath);
        Object obj = this.mappingObjs.get(sPath);
        String methodName = this.mappingValues.get(sPath).getMethod();
        Object objResult = null;
        try {
            Method method = clz.getMethod(methodName);
             objResult = method.invoke(obj);
        } catch (Exception e) {

        }
        resp.getWriter().append(objResult.toString());


    }
}
