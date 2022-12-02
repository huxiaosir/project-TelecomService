package org.joisen.consumer;

import org.joisen.common.bean.Consumer;
import org.joisen.consumer.bean.CallLogConsumer;

import java.io.IOException;

/**
 * @Author Joisen
 * @Date 2022/12/1 13:05
 * @Version 1.0
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {

        // 创建消费者
        Consumer consumer = new CallLogConsumer();

        // 消费数据
        consumer.consume();

        // 关闭资源
        consumer.close();

    }
}
