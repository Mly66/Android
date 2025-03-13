package cn.nbmly.test0310;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityExample extends AppCompatActivity {
    private static final String Test= "ActivityExample";
    Button btnclose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btnclose = findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(Test, "onResume - Activity 进入前台并交互");//进入app界面
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(Test, "onPause - Activity 失去焦点但仍可见");//进入任务管理界面
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(Test, "onStop - Activity 进入后台");//后台挂起
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(Test, "onRestart - Activity 从停止状态重新启动");//从任务管理器启动
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(Test, "onDestroy - Activity 被销毁");//退出app
    }

}
