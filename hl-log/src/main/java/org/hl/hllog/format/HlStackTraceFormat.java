package org.hl.hllog.format;

import org.hl.hllog.HlLogConfig;

/**
 * @Description: 堆栈信息处理器
 * @Author: hl
 * @Date: 2021/04/08
 **/
public class HlStackTraceFormat implements IHlLogFormat<StackTraceElement[]> {
    private HlLogConfig hiLogConfig;

    public HlStackTraceFormat(HlLogConfig hiLogConfig) {
        this.hiLogConfig = hiLogConfig;
    }

    @Override
    public String formatLog(StackTraceElement[] data) {
        StringBuilder str = new StringBuilder();
        if (data != null && data.length > 0) {
            for (int i = 0; i < data.length; i++) {
                if (i == 0) {
                    str.append("stacktrace:").append("\n");
                }
                str.append("\t├ ");
                str.append(data[i]).append("\n");
            }
        }
        return str.toString();
    }
}
