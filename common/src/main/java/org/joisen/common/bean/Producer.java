package org.joisen.common.bean;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Author Joisen
 * @Date 2022/11/30 10:12
 * @Version 1.0
 */

/**
 * 生产者接口
 */
public interface Producer extends Closeable {

    void setIn(DataIn in);
    void setOut(DataOut out);

    /**
     * 生产数据
     */
    void produce() throws IOException;
}
