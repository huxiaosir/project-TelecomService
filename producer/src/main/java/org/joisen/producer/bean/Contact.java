package org.joisen.producer.bean;

/**
 * @Author Joisen
 * @Date 2022/11/30 11:05
 * @Version 1.0
 */

import org.joisen.common.bean.Data;

/**
 * 联系人对象
 */
public class Contact extends Data {

    private String tel;
    private String name;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Object value) {
        content = (String)value;
        String[] split = content.split("\t");
        setTel(split[0]);
        setName(split[1]);
    }

    @Override
    public String toString() {
        return "Contact[ tel = " + tel + ", name = " + name + " ]";
    }
}
