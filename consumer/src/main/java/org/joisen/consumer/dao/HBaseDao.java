package org.joisen.consumer.dao;

import org.joisen.common.bean.BaseDao;
import org.joisen.common.constant.Names;

import static org.joisen.common.constant.ValConstant.REGION_COUNT;

/**
 * @Author Joisen
 * @Date 2022/12/1 20:06
 * @Version 1.0
 */

/**
 * HBase 数据访问对象
 */
public class HBaseDao extends BaseDao {


    /**
     * 初始化
     */
    public void init() throws Exception{
        start();

        createNamespaceNX(Names.NAMESPACE.getValue());
        createTableXX(Names.TABLE.getValue(), REGION_COUNT ,Names.CF_CALLER.getValue());

        end();
    }

    /**
     * 插入数据
     * @param value
     */
    public void insertData(String value) {
    }
}
