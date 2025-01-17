package com.minis.beans;

import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.util.*;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
public class ArgumentValues {

    private final List<ArgumentValue> argumentValueList = new ArrayList<>();

    public ArgumentValues() {
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
