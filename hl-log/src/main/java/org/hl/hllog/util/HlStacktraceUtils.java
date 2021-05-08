package org.hl.hllog.util;

/**
 * @Description: 堆栈信息加工过滤
 * @Author: hl
 * @Date: ${DATE} ${TIME}
 **/
public class HlStacktraceUtils {

    public static StackTraceElement[] filterStackTrace(StackTraceElement[] stackTraceElements, String packageName, int maxDep) {
        return cropStackTrace(getRealDeep(stackTraceElements, packageName, maxDep), maxDep);
    }

    /**
     * 过滤出系统层的堆栈
     *
     * @param stackTraceElements
     * @param packageName
     * @param maxDep
     * @return
     */
    public static StackTraceElement[] getRealDeep(StackTraceElement[] stackTraceElements, String packageName, int maxDep) {
        int ingNorDeep = 0;
        if (stackTraceElements != null && stackTraceElements.length > 0) {
            for (int i = stackTraceElements.length - 1; i >= 0; i--) {
                String statcPackageName = stackTraceElements[i].getClassName();
                if (statcPackageName != null && statcPackageName.startsWith(packageName)) {
                    ingNorDeep = ingNorDeep + 1;
                }
            }
        }
        int realDeep = stackTraceElements.length - ingNorDeep;
        StackTraceElement[] realStack = new StackTraceElement[realDeep];
        System.arraycopy(stackTraceElements, ingNorDeep, realStack, 0, realDeep);
        return realStack;
    }

    private static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack, int maxDepth) {
        int realDepth = callStack.length;
        if (maxDepth > 0) {
            realDepth = Math.min(maxDepth, realDepth);
        }
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(callStack, 0, realStack, 0, realDepth);
        return realStack;
    }
}
