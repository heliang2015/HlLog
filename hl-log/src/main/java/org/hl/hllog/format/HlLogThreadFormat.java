package org.hl.hllog.format;

/**
 * @Description: 线程日志处理
 * @Author: hl
 * @Date: 2020/04/08
 **/
public class HlLogThreadFormat implements IHlLogFormat<Thread> {

    @Override
    public String formatLog(Thread data) {
        return "Thread:"+data.getName();
    }
}
