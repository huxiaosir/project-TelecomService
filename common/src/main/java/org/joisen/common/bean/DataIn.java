package org.joisen.common.bean;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author Joisen
 * @Date 2022/11/30 10:14
 * @Version 1.0
 */
public interface DataIn extends Closeable {
    void setPath(String path) throws FileNotFoundException, UnsupportedEncodingException;

    Object read() throws IOException;
    <T extends Data> List<T> read(Class<T> clazz) throws IOException;
}
