package org.joisen.common.bean;

/**
 * @Author Joisen
 * @Date 2022/11/30 10:11
 * @Version 1.0
 */
// 值对象接口
public interface Val {
    void setValue(Object value);
    // 获取值方法
    Object getValue();
}
