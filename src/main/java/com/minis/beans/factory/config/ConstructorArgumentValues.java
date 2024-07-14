package com.minis.beans.factory.config;

import com.minis.beans.ArgumentValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
public class ConstructorArgumentValues {

    private final List<ArgumentValue> argumentValueList = new ArrayList<>();

    public ConstructorArgumentValues() {
    }

    public void addArgumentValue(ArgumentValue  argumentValue){
        this.argumentValueList.add(argumentValue);
    }



    public ArgumentValue getIndexedArgumentValues(int index) {
        return this.argumentValueList.get(index);
    }






    public int getArgumentCount() {
        return this.argumentValueList.size();
    }

    public boolean isEmpty() {
        return this.argumentValueList.isEmpty();
    }

}
