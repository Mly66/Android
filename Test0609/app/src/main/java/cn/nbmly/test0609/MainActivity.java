package cn.nbmly.test0609;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvBroadcastHistory;
    private ArrayAdapter<String> historyAdapter;
    private ArrayList<String> broadcastHistory;
    private MyBroadcastReceiver receiver;

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvBroadcastHistory = findViewById(R.id.lvBroadcastHistory);

        // 获取传递过来的广播历史数据
        Intent intent = getIntent();
        if (intent != null) {
            broadcastHistory = intent.getStringArrayListExtra("history");
            if (broadcastHistory == null) {
                broadcastHistory = new ArrayList<>();
            }
        }

        // 创建并设置 ArrayAdapter
        historyAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, broadcastHistory);
        lvBroadcastHistory.setAdapter(historyAdapter);

        // 注册广播接收器
        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter("cn.nbmly.MY_BROADCAST");
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销广播接收器
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    // 广播接收器类
    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("cn.nbmly.MY_BROADCAST".equals(intent.getAction())) {
                String message = intent.getStringExtra("msg");
                String broadcastInfo = "接收时间: " + System.currentTimeMillis() + ", 内容: " + message;
                broadcastHistory.add(broadcastInfo);
                historyAdapter.notifyDataSetChanged();
                Toast.makeText(context, "收到广播: " + message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}