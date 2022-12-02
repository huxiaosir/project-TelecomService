package org.joisen.common.constant;

import org.joisen.common.bean.Val;

/**
 * @Author Joisen
 * @Date 2022/11/30 10:19
 * @Version 1.0
 */

/**
 * 名称常量枚举类
 */
public enum Names implements Val {
    NAMESPACE("ct"),
    TABLE("ct:calllog"),
    CF_CALLER("caller"),
    CF_INFO("info"),
    TOPIC("ct");

    private String name;

    private Names( String name ){
        this.name = name;
    }


    @Override
    public void setValue(Object value) {
        this.name = (String) value;
    }

    @Override
    public String getValue() {
        return name;
    }
}
