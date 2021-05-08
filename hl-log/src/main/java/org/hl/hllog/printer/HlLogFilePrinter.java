package org.hl.hllog.printer;

import androidx.annotation.NonNull;

import org.hl.hllog.HlLogConfig;
import org.hl.hllog.util.DateTimeUtil;
import org.hl.hllog.util.FileStorageUtil;

import java.io.File;
import java.util.Date;

/**
 * @Description: 日志文件打印器
 * 将日志打印在文件上，需要控制文件大小;
 * @Author: hl
 * @Date: 2021/04/08
 **/
public class HlLogFilePrinter implements IHlLogPrinter {
    private String logPath;
    private String logFileName = "hlLog.txt";
    private long fileMaxSize = 200 * 1024;

    @Override
    public void print(@NonNull HlLogConfig hiLogConfig, int level, String content) {
        if (logPath == null || logPath.equals("")) {
            logPath = FileStorageUtil.getLogDirPath();
        }

        // 文件太大删除文件
        File file = new File(logPath + logFileName);
        if (file.exists()) {
            long fileSize = FileStorageUtil.getFileSize(file);
            if (fileSize > fileMaxSize) {
                FileStorageUtil.deleteFile(logPath + logFileName);
            }
        }

        StringBuilder newContent = new StringBuilder(">>===============");
        newContent.append(DateTimeUtil.formatDateTime(new Date(), DateTimeUtil.DF_MM_DD_HH_MM_SS)).append("\n");
        newContent.append(content).append("\n\n\n");
        FileStorageUtil.appendStringToFile(newContent.toString(), new File(logPath + logFileName));
    }
}
