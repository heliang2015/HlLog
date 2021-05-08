package org.hl.hllog.format;

/**
 * @Description: 日志信息处理器
 * @Author: hl
 * @Date: 2021/05/08
 **/
public interface IHlLogFormat<T> {
    String formatLog(T data);
}
