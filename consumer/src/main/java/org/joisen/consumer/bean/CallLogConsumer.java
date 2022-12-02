package org.joisen.consumer.bean;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.joisen.common.bean.Consumer;
import org.joisen.common.constant.Names;
import org.joisen.consumer.dao.HBaseDao;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Author Joisen
 * @Date 2022/12/1 19:18
 * @Version 1.0
 */

/**
 * 通话日志消费者对象
 */
public class CallLogConsumer implements Consumer {
    /**
     * 消费数据
     * @throws IOException
     */
    @Override
    public void consume(){

        try {
            // 创建配置对象
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties"));
            // 获取Flume采集的数据
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);

            // 订阅主题
            consumer.subscribe(Arrays.asList(Names.TOPIC.getValue()));

            HBaseDao dao = new HBaseDao();
            // 初始化
            dao.init();

            // 消费数据
            while(true){
                ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println(consumerRecord.value());
                    // 插入数据
                    dao.insertData(consumerRecord.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 释放资源
     * @throws IOException
     */
    @Override
    public void close() throws IOException {

    }
}
