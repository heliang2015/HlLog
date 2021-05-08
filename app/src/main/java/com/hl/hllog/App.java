package com.hl.hllog;

import android.app.Application;

import org.hl.hllog.HlLogConfig;
import org.hl.hllog.HlLogManager;
import org.hl.hllog.printer.HlLogFilePrinter;
import org.hl.hllog.printer.HlconSolePrinter;
import org.hl.hllog.printer.IHlLogPrinter;

/**
 * @Description:
 * @Author: hl
 * @Date: 2021/4/8 15:37
 **/

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HlLogManager.getInstance().init(new HlLogConfig().setOpen(true)
                        .setTag("Hl-LOG")
                        .isCludeThread(false)
                        .setTraceDeep(3),
                // 配置打印器 控制台打印，文件打印 ，视图打印需要在activity中添加
                new IHlLogPrinter[]{new HlLogFilePrinter(), new HlconSolePrinter()});
    }
}
