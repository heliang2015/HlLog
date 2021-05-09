package org.hl.hllog.printer;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hl.hl_log.R;

import org.hl.hllog.HlLogConfig;
import org.hl.hllog.util.DateTimeUtil;
import org.hl.hllog.util.Dip2PixleUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 实现在app上，可以直接查看日志，方便调试，快速排查错误；
 * @Author: hl
 * @Date: 2021/04/21
 **/
public class HlViewPrinter implements IHlLogPrinter {
    static final String FLAG_LOG_LAYOUT = "LogView";
    static final String FLAG_LOG_BUTTON = "LogFloatButton";

    private FrameLayout rootView;
    private RecyclerView mRecyclerView;
    private View logViewLayoutView;

    private Activity mActivity;
    private LogAdapter mLogAdapter;

    private List<LogModel> mDatas;
    private int defaultLogLayoutHight = 400;
    private boolean logLayoutIsShow = false;

    public HlViewPrinter(Activity context) {
        this.mActivity = context;
        init();
    }

    private void init() {
        rootView = mActivity.findViewById(android.R.id.content);
        // 添加日志控制入口
        if (rootView.findViewWithTag(FLAG_LOG_BUTTON) == null) {
            addLogFloatButton();
        }
    }

    @Override
    public void print(@NonNull HlLogConfig hiLogConfig, int level, String content) {
        // 必须在主线程
        if (!(Looper.getMainLooper().getThread().getName().equals("main"))) return;

        // 仅保持15条记录
        if (mDatas != null && mDatas.size() > 15) {
            for (int i = 0; i < mDatas.size() - 15; i++) {
                mDatas.remove(i);
            }
        }

        LogModel logModel = new LogModel(hiLogConfig.getTag(), content, level);
        logModel.setTime(DateTimeUtil.formatDateTime(new Date(), DateTimeUtil.DF_MM_DD_HH_MM_SS));
        if (mDatas == null) mDatas = new ArrayList<>();
        mDatas.add(logModel);
        if (logViewLayoutView != null) {
            mLogAdapter.notifyDataSetChanged();
            if (mDatas.size() > 0) {
                mRecyclerView.scrollToPosition(mDatas.size() - 1);
            }
        }
    }

    /**
     * 添加日志的浮动按钮
     */
    private void addLogFloatButton() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
        layoutParams.bottomMargin = Dip2PixleUtil.dp2px(mActivity, defaultLogLayoutHight);
        FrameLayout frameLayout = new FrameLayout(mActivity);
        frameLayout.setLayoutParams(layoutParams);

        View view = LayoutInflater.from(mActivity).inflate(R.layout.view_hllog_control, null);
        frameLayout.addView(view);
        rootView.addView(frameLayout);

        ImageView ivOpen = view.findViewById(R.id.iv_open);
        ivOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!logLayoutIsShow) {
                    if (rootView != null && rootView.findViewWithTag(FLAG_LOG_LAYOUT) == null) {
                        if (logViewLayoutView == null) {
                            logViewLayoutView = createLogLayoutView(rootView);
                        }
                    }
                    logLayoutIsShow = true;
                    rootView.addView(logViewLayoutView);
                }
            }
        });
    }

    /**
     * 创建rootView
     *
     * @param rootView
     * @return
     */
    private View createLogLayoutView(@NonNull final FrameLayout rootView) {
        logViewLayoutView = LayoutInflater.from(mActivity).inflate(R.layout.view_hllog_layout, null);
        LinearLayout llContent = logViewLayoutView.findViewById(R.id.ll_content);
        llContent.setClickable(true);
        llContent.setAlpha(0.8f);
        logViewLayoutView.setTag(FLAG_LOG_LAYOUT);
        ImageView ivClose = logViewLayoutView.findViewById(R.id.iv_close);
        ImageView ivClear = logViewLayoutView.findViewById(R.id.iv_clear);
        mRecyclerView = logViewLayoutView.findViewById(R.id.rcyc);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, true));
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mLogAdapter = new LogAdapter(mDatas);
        mRecyclerView.setAdapter(mLogAdapter);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logLayoutIsShow = false;
                if (logViewLayoutView != null) {
                    rootView.removeView(logViewLayoutView);
                }
            }
        });

        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatas != null && mLogAdapter != null) {
                    mDatas.clear();
                    mLogAdapter.notifyDataSetChanged();
                }
            }
        });
        return logViewLayoutView;
    }

    /**
     * 日志适配器
     */
    class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {
        private List<LogModel> mDatas;

        public LogAdapter(List<LogModel> mDatas) {
            this.mDatas = mDatas;
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hllog, null);
            return new LogViewHolder(view);
        }

        private LogModel hiLogModel;

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            hiLogModel = mDatas.get(position);
            if (hiLogModel != null) {
                holder.tag.setText("TAG: " + hiLogModel.tag);
                holder.tvTime.setText(hiLogModel.getTime());
                holder.message.setText(hiLogModel.getContent());
                holder.ivCopy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager clipboardManager = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboardManager.setText(hiLogModel.getContent());
                        Toast.makeText(mActivity, "复制成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }

    static class LogViewHolder extends RecyclerView.ViewHolder {
        TextView tag;
        TextView message;
        TextView tvTime;
        ImageView ivCopy;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tv_tag);
            message = itemView.findViewById(R.id.tv_message);
            tvTime = itemView.findViewById(R.id.tv_time);
            ivCopy = itemView.findViewById(R.id.iv_copy);
        }
    }

    class LogModel {
        private String content;
        private int level;
        private String time;
        private String tag;

        public void setTime(String time) {
            this.time = time;
        }

        public LogModel(String tag, String content, int level) {
            this.content = content;
            this.level = level;
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }

        public String getContent() {
            return content;
        }

        public int getLevel() {
            return level;
        }

        public String getTime() {
            return time;
        }
    }
}
