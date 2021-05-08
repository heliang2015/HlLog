package org.hl.hllog;

/**
 * @Description: HlLog 配置类
 * @Author: hl
 * @Date: 2021/04/08
 **/
public class HlLogConfig {

    // 默认全局tag
    private String tag = "HLLog";
    // 日志开关 默认关闭
    private boolean isOpen = false;
    // 是否打印线程信息
    private boolean isIncludeThread = false;
    // 默认堆栈深度为5
    private int traceDeep = 5;

    private JsonParse mJsonParse;

    public void setIncludeThread(boolean includeThread) {
        isIncludeThread = includeThread;
    }

    public JsonParse getmJsonParse() {
        return mJsonParse;
    }

    public HlLogConfig setmJsonParse(JsonParse mJsonParse) {
        this.mJsonParse = mJsonParse;
        return this;
    }

    public int getTraceDeep() {
        return traceDeep;
    }

    public HlLogConfig setTraceDeep(int traceDeep) {
        this.traceDeep = traceDeep;
        return this;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public HlLogConfig setOpen(boolean open) {
        isOpen = open;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public HlLogConfig setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public HlLogConfig isCludeThread(boolean isIncludeThread) {
        this.isIncludeThread = isIncludeThread;
        return this;
    }

    public boolean isIncludeThread() {
        return isIncludeThread;
    }

    public interface JsonParse {
        String toJson(Object src);
    }
}
