package org.hl.hllog;

import android.util.Log;

import androidx.annotation.NonNull;

import org.hl.hllog.printer.IHlLogPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Description: 日志管理类
 * @Author: hl
 * @Date: 2021/04/08
 **/
public class HlLogManager {

    private static String gloablTag;
    private static HlLogManager mHlLogManager;

    // 配置组件
    private HlLogConfig mHilogConfig;
    // 打印器组件
    private List<IHlLogPrinter> mLogPrinters = new ArrayList<>();

    private HlLogManager() {}

    public static HlLogManager getInstance() {
        if (mHlLogManager == null) {
            mHlLogManager = new HlLogManager();
        }
        return mHlLogManager;
    }

    /**
     * 初始化日志管理器
     *
     * @param hilogConfig    配置管理
     * @param iHlLogPrinters 打印器
     */
    public void init(@NonNull HlLogConfig hilogConfig, @NonNull IHlLogPrinter[] iHlLogPrinters) {
        Log.i(hilogConfig.getTag(), "...日志模块初始化...");
        mHilogConfig = hilogConfig;
        String tag = hilogConfig.getTag();
        if (tag != null && !tag.equals("")) {
            gloablTag = tag;
        } else {
            gloablTag = hilogConfig.getTag();
        }
        mLogPrinters.addAll(Arrays.asList(iHlLogPrinters));
    }

    public static String getDefaultTag() {
        return gloablTag;
    }

    public HlLogConfig getmHilogConfig() {
        return mHilogConfig;
    }

    public void addPrinter(IHlLogPrinter printer) {
        mLogPrinters.add(printer);
    }

    public void removePrinter(IHlLogPrinter printer) {
        mLogPrinters.remove(printer);
    }

    public List<IHlLogPrinter> getmLogPrinters() {
        return mLogPrinters;
    }
}
