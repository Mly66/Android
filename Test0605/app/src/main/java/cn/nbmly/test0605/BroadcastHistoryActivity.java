package cn.nbmly.test0605;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BroadcastHistoryActivity extends AppCompatActivity {

    private ListView lvBroadcastHistory;
    private ArrayAdapter<String> historyAdapter;
    private ArrayList<String> broadcastHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_history);

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
    }
}