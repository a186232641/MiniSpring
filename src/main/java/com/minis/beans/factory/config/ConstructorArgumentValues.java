package com.minis.beans.factory.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
public class ConstructorArgumentValues {

    private final List<ConstructorArgumentValue> argumentValueList = new ArrayList<>();

    public ConstructorArgumentValues() {
    }

    public void addConstructorArgumentValue(ConstructorArgumentValue constructorArgumentValue){
        this.argumentValueList.add(constructorArgumentValue);
    }



    public ConstructorArgumentValue getIndexedArgumentValues(int index) {
        return this.argumentValueList.get(index);
    }






    public int getArgumentCount() {
        return this.argumentValueList.size();
    }

    public boolean isEmpty() {
        return this.argumentValueList.isEmpty();
    }

}
