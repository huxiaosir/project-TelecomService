package org.joisen.common.bean;

/**
 * @Author Joisen
 * @Date 2022/11/30 10:17
 * @Version 1.0
 */

/**
 * 数据对象
 */
public abstract class Data implements Val{

    // 数据内容
    public String content;


    @Override
    public void setValue(Object value) {
        content = (String)value;
    }

    @Override
    public String getValue() {
        return content;
    }
}
