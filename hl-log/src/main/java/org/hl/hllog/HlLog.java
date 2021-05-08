package org.hl.hllog;

import android.util.Log;

import androidx.annotation.NonNull;


import org.hl.hllog.format.HlLogThreadFormat;
import org.hl.hllog.format.HlStackTraceFormat;
import org.hl.hllog.printer.IHlLogPrinter;
import org.hl.hllog.util.HlStacktraceUtils;

import java.util.List;

/**
 * @Description: 日志打印管理类
 * 实现控制台打印，文件打印，前端视图打印
 * - 支持配置打印堆栈深度；
 * - 支持配置是否打印线程信息；
 * - 支持配置日志格式化器；
 * @Author: hl
 * @Date:2021/04/08
 **/
public class HlLog {

    private static final String HI_LOG_PACKAGE;

    static {
        String className = HlLog.class.getName();
        HI_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

    public static void i(Object... contents) {
        it(HlLogManager.getDefaultTag(), contents);
    }

    public static void it(String tag, Object... contents) {
        log(tag, HlLogType.I, contents);
    }

    public static void d(Object... contents) {
        dt(HlLogManager.getDefaultTag(), contents);
    }

    public static void dt(String tag, Object... contents) {
        log(tag, HlLogType.D, contents);
    }

    public static void e(Object... contents) {
        et(HlLogManager.getDefaultTag(), contents);
    }

    public static void et(String tag, Object... contents) {
        log(tag, HlLogType.E, contents);
    }

    public static void v(Object... contents) {
        vt(HlLogManager.getDefaultTag(), contents);
    }

    public static void vt(String tag, Object... contents) {
        log(tag, HlLogType.V, contents);
    }

    public static void w(Object... contents) {
        wt(HlLogManager.getDefaultTag(), contents);
    }

    public static void wt(String tag, Object... contents) {
        log(tag, HlLogType.W, contents);
    }

    public static void a(Object... contents) {
        at(HlLogManager.getDefaultTag(), contents);
    }

    public static void at(String tag, Object... contents) {
        log(tag, HlLogType.A, contents);
    }

    /**
     * 打印日志
     *
     * @param tag      日志tag
     * @param level    日志等级
     * @param contents 日志内容
     */
    public static void log(String tag, @HlLogType.TYPE int level, Object... contents) {
        // 实现打印
        HlLogManager hlLogManager = HlLogManager.getInstance();
        HlLogConfig mHilogConfig = hlLogManager.getmHilogConfig();
        mHilogConfig.setTag(tag);
        if (HlLogManager.getInstance().getmHilogConfig() == null) {
            Log.i(tag, "HLlog日志模块未初始化...");
            return;
        }

        if (!mHilogConfig.isOpen()) return;

        // 组装格式化日志信息
        String msg = dealLogMsg(mHilogConfig, level, contents);

        // 开始循环遍历日志打印器（文件打印，控制台打印，View打印...）
        List<IHlLogPrinter> mHiprints = hlLogManager.getmLogPrinters();
        if (mHiprints != null && mHiprints.size() > 0) {
            for (IHlLogPrinter iHlLogPrinter : mHiprints) {
                iHlLogPrinter.print(mHilogConfig, level, msg);
            }
        }
    }

    /**
     * 处理日志信息
     * 最终要处理成String 序列化
     * 线程序列化
     * 堆栈信息序列化
     */
    private static String dealLogMsg(@NonNull HlLogConfig mHilogConfig, @HlLogType.TYPE int level, Object... cotents) {
        StringBuilder str = new StringBuilder();
        // 是否打印线程信息
        if (mHilogConfig.isIncludeThread()) {
            str.append(new HlLogThreadFormat().formatLog(Thread.currentThread())).append("\n");
        }

        // 是否打印堆栈信息(深度大于0)
        if (mHilogConfig.getTraceDeep() > 0) {
            str.append(new HlStackTraceFormat(mHilogConfig).formatLog(HlStacktraceUtils.filterStackTrace(new Throwable().getStackTrace(), HI_LOG_PACKAGE, mHilogConfig.getTraceDeep())));
        }

        str.append(parseBody(mHilogConfig, cotents));
        return str.toString();
    }

    /**
     * 日志解析
     * 判断是否有配置日志解析器，有使用日志解析器解析。
     *
     * @param cotents
     * @return
     */
    public static String parseBody(@NonNull HlLogConfig hiLogConfig, Object... cotents) {
        StringBuilder stringBuilder = new StringBuilder();
        if (hiLogConfig.getmJsonParse() == null) {
            // 本土解析
            if (cotents != null && cotents.length > 0) {
                for (int i = 0; i < cotents.length; i++) {
                    stringBuilder.append(cotents[i].toString()).append("\n");
                }
            }
        } else {
            if (cotents != null && cotents.length > 0) {
                if (cotents.length == 1 && cotents[0] instanceof String) {
                    return cotents[0].toString();
                }
                return hiLogConfig.getmJsonParse().toJson(cotents);
            }
        }
        return stringBuilder.toString();
    }
}
