package org.joisen.producer.bean;

/**
 * @Author Joisen
 * @Date 2022/11/30 10:32
 * @Version 1.0
 */

import org.joisen.common.bean.DataIn;
import org.joisen.common.bean.DataOut;
import org.joisen.common.bean.Producer;
import org.joisen.common.util.DateUtil;
import org.joisen.common.util.NumberUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 本地数据文件生产者
 */
public class LocalFileProducer implements Producer {

    private DataIn in;
    private DataOut out;
    private volatile boolean flg = true;

    @Override
    public void setIn(DataIn in) {
        this.in = in;
    }

    @Override
    public void setOut(DataOut out) {
        this.out = out;
    }

    /**
     * 生产数据
     */
    @Override
    public void produce() throws IOException {

        try {
            // 读取通讯录数据
            List<Contact> contactList = in.read(Contact.class);

//            for (Contact contact : contactList) {
//                System.out.println(contact);
//            }
            while(flg){
                // 从通讯录中随机查找两个电话号码（主叫， 被叫）
                int call1Index = new Random().nextInt(contactList.size());
                int call2Index;
                while(true){
                    call2Index = new Random().nextInt(contactList.size());
                    if(call1Index != call2Index) break;
                }
                Contact call1 = contactList.get(call1Index);
                Contact call2 = contactList.get(call2Index);


                // 生成随机的通话时间 时间范围为2022年内
                String startDate = "20220101000000";
                String endDate = "20230101000000";
                long startTime = DateUtil.parse(startDate, "yyyyMMddHHmmss").getTime();
                long endTime = DateUtil.parse(endDate,"yyyyMMddHHmmss").getTime();

                // 通话时间
                long callTime = startTime + (long)((endTime - startTime) * Math.random());

                // 通话时间字符串
                String callTimeStr = DateUtil.format(new Date(callTime), "yyyyMMddHHmmss");

                // 生成随机的通话时长
                // 需要将得到的数字进行格式化 变成统一4位数的长度 可以采用字符串拼接的方式 也可以采用 DecimalFormat 进行格式化
                String duration = NumberUtil.format(new Random().nextInt(3000), 4);

                // 生成通话记录
                CallLog log = new CallLog(call1.getTel(), call2.getTel(), callTimeStr, duration);
                System.out.println(log);
                // 将通话记录刷写到数据文件中
                out.write(log);

                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭生产者
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if (in != null) in.close();
        if(out != null) out.close();
    }
}
