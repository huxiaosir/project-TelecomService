package org.joisen.producer.io;

import org.joisen.common.bean.DataOut;

import java.io.*;

/**
 * @Author Joisen
 * @Date 2022/11/30 10:38
 * @Version 1.0
 */

/**
 * 本地文件数据输出
 */
public class LocalFileDataOut implements DataOut {

    private PrintWriter writer = null;
    public LocalFileDataOut(String path){
        setPath(path);
    }
    @Override
    public void setPath(String path) {
        try {
            writer = new PrintWriter( new PrintWriter( new OutputStreamWriter(new FileOutputStream(path), "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Object data) throws Exception {
        write(data.toString());
    }

    /**
     * 将数据字符串生成到文件中
     * @param data
     * @throws Exception
     */
    @Override
    public void write(String data) throws Exception {
        writer.println(data);
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        if(writer != null) writer.close();
    }
}
