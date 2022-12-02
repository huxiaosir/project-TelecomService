package org.joisen.producer.bean;

/**
 * @Author Joisen
 * @Date 2022/11/30 15:51
 * @Version 1.0
 */

import lombok.*;

/**
 * 通话记录对象
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class CallLog {

    private String call1;

    private String call2;

    private String callTime;

    private String duration;

    public String toString(){
        return call1 + "\t" + call2 + "\t" + callTime + "\t" + duration;
    }
}
