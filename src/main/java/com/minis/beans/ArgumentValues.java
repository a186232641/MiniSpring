package com.minis.beans;

import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/11
 */
public class ArgumentValues {
    private final Map<Integer, ArgumentValue> indexedArgumentValues = new HashMap<>();
    private final List<ArgumentValue> genericArgumentValues = new LinkedList<>();

    public ArgumentValues() {
    }

    private void addArgumentValue(Integer key, ArgumentValue argumentValue) {
        indexedArgumentValues.put(key, argumentValue);
    }

    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }

    public ArgumentValue getIndexedArgumentValues(int index) {
        return this.indexedArgumentValues.get(index);
    }

    private void addGenericArgumentValue(Object value, String type) {
        this.genericArgumentValues.add(new ArgumentValue(value, type));
    }

    private void addGenericArgumentValue(ArgumentValue argumentValue) {
        if (argumentValue.getName() != null) {
            for (ArgumentValue genericArgumentValue : genericArgumentValues) {
                if (genericArgumentValue.getName().equals(argumentValue.getName())) {
                    genericArgumentValues.remove(argumentValue.getName());
                }
            }
        }
        this.genericArgumentValues.add(argumentValue);
    }

    public ArgumentValue genericArgumentValue(String requiredName) {
        for (ArgumentValue argumentValue : genericArgumentValues) {
            if (argumentValue.getName().equals(argumentValue.getName())) {
                return argumentValue;
            }
        }
        return null;
    }

    public int getArgumentCount() {
        return this.genericArgumentValues.size();
    }

    public boolean isEmpty() {
        return this.genericArgumentValues.isEmpty();
    }

}
