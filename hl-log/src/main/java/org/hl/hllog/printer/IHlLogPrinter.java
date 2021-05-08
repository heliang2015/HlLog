package org.hl.hllog.printer;

import androidx.annotation.NonNull;

import org.hl.hllog.HlLogConfig;

/**
 * @Description: 日志打印器
 * @Author: hl
 * @Date: 2021/04/08
 **/
public interface IHlLogPrinter {
    void print(@NonNull HlLogConfig hiLogConfig, int level, String content);
}
