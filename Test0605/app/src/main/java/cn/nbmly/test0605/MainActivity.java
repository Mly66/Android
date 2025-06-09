package cn.nbmly.test0605;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyReceiver receiver;
    private TextView tvMessage;
    private Button btnSend;
    private EditText editText;
    private TextView tvHighPriority;
    private TextView tvMiddlePriority;
    private TextView tvLowPriority;
    private int num = 0;
    private HighPriorityReceiver highPriorityReceiver;
    private MiddlePriorityReceiver middlePriorityReceiver;
    private LowPriorityReceiver lowPriorityReceiver;

    private Button btnViewHistory;
    private ArrayList<String> broadcastHistory = new ArrayList<>();

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvMessage = findViewById(R.id.tvMessage);
        btnSend = findViewById(R.id.btnSend);
        editText = findViewById(R.id.etInput);
        tvHighPriority = findViewById(R.id.tvHighPriority);
        tvMiddlePriority = findViewById(R.id.tvMiddlePriority);
        tvLowPriority = findViewById(R.id.tvLowPriority);
        btnViewHistory = findViewById(R.id.btnViewHistory);

        // 初始化并注册广播接收器
        receiver = new MyReceiver(tvMessage);
        IntentFilter filter = new IntentFilter();
        filter.addAction("cn.nbmly.MY_BROADCAST");
        registerReceiver(receiver, filter);

        // 注册高优先级接收器
        highPriorityReceiver = new HighPriorityReceiver();
        IntentFilter highFilter = new IntentFilter();
        highFilter.addAction("cn.nbmly.MY_BROADCAST");
        highFilter.setPriority(1000); // 设置高优先级
        registerReceiver(highPriorityReceiver, highFilter);

        // 注册中优先级接收器
        middlePriorityReceiver = new MiddlePriorityReceiver();
        IntentFilter middleFilter = new IntentFilter();
        middleFilter.addAction("cn.nbmly.MY_BROADCAST");
        middleFilter.setPriority(500); // 设置中优先级
        registerReceiver(middlePriorityReceiver, middleFilter);

        // 注册低优先级接收器
        lowPriorityReceiver = new LowPriorityReceiver();
        IntentFilter lowFilter = new IntentFilter();
        lowFilter.addAction("cn.nbmly.MY_BROADCAST");
        lowFilter.setPriority(100); // 设置低优先级
        registerReceiver(lowPriorityReceiver, lowFilter);

        btnSend.setOnClickListener(v -> {
            String text = editText.getText().toString();
            String message = "发送时间: " + System.currentTimeMillis() + ", 内容: " + text;
            broadcastHistory.add(message);
            Intent intent = new Intent("cn.nbmly.MY_BROADCAST");
            intent.putExtra("msg", text);
            sendOrderedBroadcast(intent, null); // 使用有序广播
        });

        btnViewHistory.setOnClickListener(v -> {
            Intent historyIntent = new Intent(MainActivity.this, BroadcastHistoryActivity.class);
            historyIntent.putStringArrayListExtra("history", broadcastHistory);
            startActivity(historyIntent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        unregisterReceiver(highPriorityReceiver);
        unregisterReceiver(middlePriorityReceiver);
        unregisterReceiver(lowPriorityReceiver);
    }

    class HighPriorityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("cn.nbmly.MY_BROADCAST".equals(intent.getAction())) {
                num = num + 1;
                String msg = intent.getStringExtra("msg");
                tvHighPriority.setText("高优先级广播接收到的数据: " + num + msg);

            }

        }
    }

    class MiddlePriorityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("cn.nbmly.MY_BROADCAST".equals(intent.getAction())) {
                num = num + 1;
                String msg = intent.getStringExtra("msg");
                tvMiddlePriority.setText("中优先级广播接收到的数据: " + num + msg);
            }
        }
    }

    class LowPriorityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("cn.nbmly.MY_BROADCAST".equals(intent.getAction())) {
                num = num + 1;
                String msg = intent.getStringExtra("msg");
                tvLowPriority.setText("低优先级广播接收到的数据: " + num + msg);
            }
        }
    }
}
