package org.hl.hllog;

import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Description: log 常量
 * @Author: hl
 * @Date: 2021/04/08
 **/
public class HlLogType {

    @IntDef({V, I, E, D, W, A})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    public static final int V = Log.VERBOSE;
    public static final int I = Log.INFO;
    public static final int E = Log.ERROR;
    public static final int D = Log.DEBUG;
    public static final int W = Log.WARN;
    public static final int A = Log.ASSERT;
}
