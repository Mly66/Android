package cn.nbmly.testducks;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private MyBroadcastReceiverOne broadcastReceiverOne;
    private MyBroadcastReceiverTwo broadcastReceiverTwo;
    private MyBroadcastReceiverThree broadcastReceiverThree;
    private ImageView imageViewHorn;
    private TextView textViewLeftContent;
    private TextView textViewOne;
    private TextView textViewTwo;
    private TextView textViewThree;
    private ImageView imageViewRefresh;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        registerReceiver();
    }

    private void init() {
        imageViewHorn = findViewById(R.id.iv_horn);
        textViewLeftContent = findViewById(R.id.tv_left_content);
        textViewOne = findViewById(R.id.tv_one);
        textViewTwo = findViewById(R.id.tv_two);
        textViewThree = findViewById(R.id.tv_three);
        imageViewRefresh = findViewById(R.id.iv_refresh);

        imageViewHorn.setOnClickListener(view -> {
            textViewLeftContent.setVisibility(View.VISIBLE);
            imageViewHorn.setClickable(false);
            Intent intent = new Intent();
            intent.setAction("Count_Ducks");
            sendOrderedBroadcast(intent, null);
        });

        imageViewRefresh.setOnClickListener(view -> {
            resetCounters();
        });
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private void registerReceiver() {
        broadcastReceiverTwo = new MyBroadcastReceiverTwo();
        IntentFilter filterTwo = new IntentFilter();
        filterTwo.setPriority(1000);
        filterTwo.addAction("Count_Ducks");
        registerReceiver(broadcastReceiverTwo, filterTwo);
        broadcastReceiverOne = new MyBroadcastReceiverOne();
        IntentFilter filterOne = new IntentFilter();
        filterOne.setPriority(1000);
        filterOne.addAction("Count_Ducks");
        registerReceiver(broadcastReceiverOne, filterOne);
        broadcastReceiverThree = new MyBroadcastReceiverThree();
        IntentFilter filterThree = new IntentFilter();
        filterThree.setPriority(600);
        filterThree.addAction("Count_Ducks");
        registerReceiver(broadcastReceiverThree, filterThree);
    }

    class MyBroadcastReceiverOne extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            textViewOne.setVisibility(View.VISIBLE);
            counter = counter + 1;
            textViewOne.setText(counter + "");
            Log.i("BroadcastReceiverOne", "广播接收者One,接收到了广播消息");
            delay();
        }
    }

    class MyBroadcastReceiverTwo extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            textViewTwo.setVisibility(View.VISIBLE);
            counter = counter + 1;
            textViewTwo.setText(counter + "");
            Log.i("BroadcastReceiverTwo", "广播接收者Two,接收到了广播消息");
            delay();
        }
    }

    class MyBroadcastReceiverThree extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            textViewThree.setVisibility(View.VISIBLE);
            counter = counter + 1;
            textViewThree.setText(counter + "");
            Log.i("BroadcastReceiverThree", "广播接收者Three,接收到了广播消息");
            delay();
        }
    }

    private void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void resetCounters() {
        counter = 0;
        textViewOne.setVisibility(View.GONE);
        textViewTwo.setVisibility(View.GONE);
        textViewThree.setVisibility(View.GONE);
        textViewLeftContent.setVisibility(View.GONE);
        imageViewHorn.setClickable(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiverOne);
        unregisterReceiver(broadcastReceiverTwo);
        unregisterReceiver(broadcastReceiverThree);
    }
}