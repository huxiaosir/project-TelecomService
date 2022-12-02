package org.joisen.common.bean;

/**
 * @Author Joisen
 * @Date 2022/12/1 20:05
 * @Version 1.0
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;
import org.joisen.common.constant.Names;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 基础数据访问对象
 */
public abstract class BaseDao {

    private ThreadLocal<Connection> connHolder = new ThreadLocal<>();
    private ThreadLocal<Admin> adminHolder = new ThreadLocal<>();

    protected void start() throws Exception{
        getConnection();
        getAdmin();
    }

    protected void end() throws Exception{
        Admin admin = getAdmin();
        if(admin != null){
            admin.close();
            adminHolder.remove();
        }

        Connection conn = getConnection();
        if (conn != null){
            conn.close();
            connHolder.remove();
        }
    }

    /**
     * 删除表
     * @param name
     * @throws Exception
     */
    protected void deleteTable(String name) throws Exception{
        TableName tableName = TableName.valueOf(name);
        Admin admin = getAdmin();
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }

    private void createTable(String name, Integer regionCount, String... families) throws Exception{
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        if (families.length == 0 || families == null) {
            families = new String[1];
            families[0] = Names.CF_INFO.getValue();
        }
        for (String family : families) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }
        // 增加预分区
        if (regionCount == null || regionCount <= 1){
            admin.createTable(hTableDescriptor);
            admin.createTable(hTableDescriptor);
        }else {
            // 分区键
            byte[][] splitKeys = genSplitKeys(regionCount);
            admin.createTable(hTableDescriptor, splitKeys);
        }
    }

    /**
     * 生成分区键
     * @param regionCount
     * @return
     */
    private byte[][] genSplitKeys(Integer regionCount){
        int splitKeyCount = regionCount - 1;
        byte[][] bs = new byte[splitKeyCount][];
        ArrayList<byte[]> bsList = new ArrayList<>();
        for (int i = 0; i < splitKeyCount; i++) {
            String splitKey = i + "|";
            System.out.println(splitKey);
            bsList.add(Bytes.toBytes(splitKey));
        }
        bsList.toArray(bs);
        return bs;
    }

    /**
     * 不管有没有该表都会创建，有则删除再创建
     * @param name
     * @param families
     */
    protected void createTableXX(String name, String... families) throws Exception{
        createTableXX(name, null, families);
    }
    protected void createTableXX(String name, Integer regionCount, String... families) throws Exception{
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);

        if (admin.tableExists(tableName)) {
            // 表存在，删除表
            deleteTable(name);
        }
        // 创建表
        createTable(name, regionCount, families);
    }

    /**
     * 创建命名空间，不存在则创建
     * @param namespace
     */
    protected void createNamespaceNX(String namespace) throws Exception{
        Admin admin = getAdmin();
        try {
            admin.getNamespaceDescriptor(namespace); // 当不存在此namespace时会抛出异常，故在catch中进行创建
        } catch (IOException e) {
            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespace).build();
            admin.createNamespace(namespaceDescriptor);
        }
    }

    /**
     * 获取连接对象
     * @return
     */
    protected synchronized Admin getAdmin() throws Exception{
        Admin admin = adminHolder.get();
        if(admin == null){
            admin = getConnection().getAdmin();
            adminHolder.set(admin);
        }
        return admin;
    }

    /**
     * 获取连接对象
     * @return
     */
    protected synchronized Connection getConnection() throws Exception{
        Connection conn = connHolder.get();
        if(conn == null){
            Configuration conf = HBaseConfiguration.create();
            conn = ConnectionFactory.createConnection(conf);
            connHolder.set(conn);
        }
        return conn;
    }

    public static void main(String[] args) {
//        System.out.println(genSplitKeys(6)); // 将方法改成static调用
    }

}
