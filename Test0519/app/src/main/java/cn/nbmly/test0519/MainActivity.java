package cn.nbmly.test0519;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button sendMessage;
    NotificationManager manager;
    String cid1 ="ch01";
    NotificationCompat.Builder builder ;




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
        sendMessage = findViewById(R.id.sendMessage);
        sendMessage.setOnClickListener(v -> {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(cid1, "channel1", NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(channel);
                builder = new NotificationCompat.Builder(this, cid1);
            } else {
                builder = new NotificationCompat.Builder(this);
            }

            Intent intent = new Intent(this, MainActivity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            String Text= "k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡";
            builder
                    .setContentTitle("大儿，又来装逼(▼へ▼メ)了")
                    .setContentText("k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡k毛鸡")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.icon1)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.calendar))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setTicker("ticker")
                    .setNumber(1)
                    .setStyle(new NotificationCompat.BigTextStyle())
                    .setContentIntent(pendingIntent)  // 点击通知主体时触发
                    .setAutoCancel(true)                     // 点击主体后自动消失
                    .addAction(R.drawable.icon1,"nb",pendingIntent);
//                    .setContentIntent(pendingIntent)  // 点击跳转


            Notification notification = builder.build();
            manager.notify(100, notification);
        });

    }
}