package com.hl.hllog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import org.hl.hllog.HlLog;
import org.hl.hllog.HlLogManager;
import org.hl.hllog.printer.HlViewPrinter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HlViewPrinter hlViewPrinter = new HlViewPrinter(this);
        HlLogManager.getInstance().addPrinter(hlViewPrinter);

        findViewById(R.id.btn_hl_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HlLog.d("hello world! i am hllog!");
            }
        });
    }


}
