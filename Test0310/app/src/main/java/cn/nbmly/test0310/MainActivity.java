package cn.nbmly.test0310;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    // 声明UI组件
    private TextView countTextView;
    private Button increaseButton;
    private Button decreaseButton;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate - Activity 创建");//打开app
        // 初始化UI组件
        countTextView = findViewById(R.id.countTextView);
        increaseButton = findViewById(R.id.increaseButton);
        decreaseButton = findViewById(R.id.decreaseButton);

        // 设置增加按钮点击事件
        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                updateCountDisplay();
                // 当计数达到10的时候显示一个提示
                if (count == 10) {
                    Toast.makeText(MainActivity.this, getString(R.string.toast_reached_ten), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 设置减少按钮点击事件
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 防止计数变为负数
                if (count > 0) {
                    count--;
                    updateCountDisplay();
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.toast_cannot_be_negative), Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume - Activity 进入前台并交互");//进入app界面
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause - Activity 失去焦点但仍可见");//进入任务管理界面
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop - Activity 进入后台");//后台挂起
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart - Activity 从停止状态重新启动");//从任务管理器启动
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy - Activity 被销毁");//退出app
    }

    // 更新计数显示
    private void updateCountDisplay() {
        countTextView.setText(String.valueOf(count));
    }
}
