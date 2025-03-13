package cn.nbmly.test0310;

import android.content.Intent;
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

    private Button linkButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate - Activity 创建");//打开app
        // 初始化UI组件
        countTextView = findViewById(R.id.countTextView);
        increaseButton = findViewById(R.id.increaseButton);
        decreaseButton = findViewById(R.id.decreaseButton);
        linkButton = findViewById(R.id.linkButton);

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

        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityExample.class);
                startActivity(intent);
            }
        });
    }



    // 更新计数显示
    private void updateCountDisplay() {
        countTextView.setText(String.valueOf(count));
    }
}
