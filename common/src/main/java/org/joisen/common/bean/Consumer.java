package org.joisen.common.bean;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Author Joisen
 * @Date 2022/12/1 19:15
 * @Version 1.0
 */

/**
 * 消费者接口
 */
public interface Consumer extends Closeable {
    /**
     * 消费数据
     */
    void consume() throws IOException;

}
