package com.minis.core.env;

/**
 * @author 韩飞龙
 * @version 1.0
 * 2024/7/15
 */
public interface Environment extends PropertyResolver{
    String[] getActiveProfiles();
    String[] getDefalutProfiles();
    boolean acceptProfiles();

}
