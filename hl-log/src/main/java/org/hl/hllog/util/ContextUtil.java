package org.hl.hllog.util;

import android.content.Context;

import java.lang.reflect.Method;

/**
 * @Description: 通过反射获取context,实现解耦
 * @Author: hl
 * @Date: 2021/4/21
 **/
public class ContextUtil {
    private static Context CONTEXT_INSTANCE;

    /**
     * 取得Context对象
     * PS:必须在主线程调用
     *
     * @return Context
     */
    public static Context getContext() {
        if (CONTEXT_INSTANCE == null) {
            synchronized (ContextUtil.class) {
                if (CONTEXT_INSTANCE == null) {
                    try {
                        Class<?> ActivityThread = Class.forName("android.app.ActivityThread");

                        Method method = ActivityThread.getMethod("currentActivityThread");
                        Object currentActivityThread = method.invoke(ActivityThread);//获取currentActivityThread 对象

                        Method method2 = currentActivityThread.getClass().getMethod("getApplication");
                        CONTEXT_INSTANCE = (Context) method2.invoke(currentActivityThread);//获取 Context对象

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return CONTEXT_INSTANCE;
    }
}
