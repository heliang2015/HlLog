package org.hl.hllog.printer;

import android.util.Log;
import androidx.annotation.NonNull;
import org.hl.hllog.HlLogConfig;

/**
 * @author hl
 * 版本: 1.0
 * 创建日期：2021/04/21
 * 描述：控制台打印器
 *
 */
public class HlconSolePrinter implements IHlLogPrinter {
    @Override
    public void print(@NonNull HlLogConfig hiLogConfig, int level, String content) {
        Log.println(level,hiLogConfig.getTag(),content);
    }
}
