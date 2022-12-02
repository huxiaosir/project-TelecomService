package org.joisen.producer;

/**
 * @Author Joisen
 * @Date 2022/11/30 10:30
 * @Version 1.0
 */

import org.joisen.common.bean.Producer;
import org.joisen.producer.bean.LocalFileProducer;
import org.joisen.producer.io.LocalFileDataIn;
import org.joisen.producer.io.LocalFileDataOut;

import java.io.IOException;

/**
 * 启动对象
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {

        if( args.length < 2 ) {
            System.out.println("系统参数不正确，请按指定格式传递： java -jar Produce.jar path1 path2 ");
            System.exit(1);
        }

        // 构建生产者对象
        Producer producer = new LocalFileProducer();

        // 生产数据

//        producer.setIn( new LocalFileDataIn("D:\\product\\JAVA\\Project\\project-TelecomService\\data\\contact.log"));
//        producer.setOut( new LocalFileDataOut("D:\\product\\JAVA\\Project\\project-TelecomService\\data\\call.log"));

        producer.setIn(new LocalFileDataIn(args[0]));
        producer.setOut(new LocalFileDataOut(args[1]));

        producer.produce();
        // 关闭生产者对象
        producer.close();

    }
}
