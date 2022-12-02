package org.joisen.common.constant;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Author Joisen
 * @Date 2022/12/1 21:51
 * @Version 1.0
 */
public class ConfigConstant {
    private static Map<String, String> valueMap = new HashMap<>();

    static {

        ResourceBundle ct = ResourceBundle.getBundle("ct");
        Enumeration<String> enums = ct.getKeys();
        while (enums.hasMoreElements()) {
            String key = enums.nextElement();
            String value = ct.getString(key);
            valueMap.put(key, value);
        }
    }

    public static String getVal(String key){
        return valueMap.get(key);
    }

    public static void main(String[] args) {
        System.out.println(ConfigConstant.valueMap.get("ct.cf.caller"));
    }

}
