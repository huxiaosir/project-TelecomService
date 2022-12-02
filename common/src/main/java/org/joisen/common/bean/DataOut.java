package org.joisen.common.bean;

import java.io.Closeable;

/**
 * @Author Joisen
 * @Date 2022/11/30 10:14
 * @Version 1.0
 */
public interface DataOut extends Closeable {
    void setPath(String path);

    void write(Object data) throws Exception;

    void write(String data) throws Exception;
}
