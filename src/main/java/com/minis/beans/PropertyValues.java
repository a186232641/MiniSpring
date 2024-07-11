package com.minis.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
public class PropertyValues {
    private final List<PropertyValue> propertyVasluesList;

    public PropertyValues() {
        this.propertyVasluesList = new ArrayList<>();
    }
    public  List<PropertyValue> getPropertyValueList(){
        return this.propertyVasluesList;
    }
    public int size(){
        return  this.propertyVasluesList.size();
    }
    public void addPropertyValue(PropertyValue py){
        this.propertyVasluesList.add(py);
    }
    public void addPropertyValue(String propertyName,Object propertyValue){
        this.propertyVasluesList.add(new PropertyValue(propertyName,propertyValue));
    }
    public void removePropertyValue(PropertyValue propertyValue){
        this.propertyVasluesList.remove(propertyValue);
    }
    public void removePropertyValue(String  name){
        for (PropertyValue propertyValue : propertyVasluesList) {
            if(propertyValue.getName().equals(name)){
                this.propertyVasluesList.remove(propertyValue);
            }
        }

    }
    public PropertyValue[] getPropertyValue(){
        return (PropertyValue[]) propertyVasluesList.toArray();
    }
    public PropertyValue getropertyValue(String  name){
        for (PropertyValue propertyValue : propertyVasluesList) {
            if(propertyValue.getName().equals(name)){
                return propertyValue;
            }
        }
return  null;
    }
    public boolean contains(String properyuName){
        return  getropertyValue(properyuName)==null;
    }
    public boolean isEmpty(){
        return this.propertyVasluesList.isEmpty();
    }
}
