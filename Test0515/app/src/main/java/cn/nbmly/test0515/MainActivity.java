package cn.nbmly.test0515;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CALL_PHONE = 1;
    private static final String CHANNEL_ID = "channel_01";
    private static final int NOTIFICATION_ID = 1001;

    private Button btnOpenUrl;
    private Button btnDialPhone;
    private Button btnShowNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenUrl = findViewById(R.id.btn_open_url);
        btnDialPhone = findViewById(R.id.btn_dial_phone);
        btnShowNotification = findViewById(R.id.btn_show_notification);

        btnOpenUrl.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
            startActivity(intent);
        });

        btnDialPhone.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_CALL_PHONE
                );
            } else {
                dialNumber("1234567890");
            }
        });

        btnShowNotification.setOnClickListener(v -> showNotification());
    }


    private void showNotification() {
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager == null) {
            Toast.makeText(this, "无法获取 NotificationManager", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                        ? PendingIntent.FLAG_IMMUTABLE
                        : PendingIntent.FLAG_UPDATE_CURRENT
        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "默认通知渠道",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("用于示例的默认通知渠道");
            manager.createNotificationChannel(channel);

            Notification.Builder builder = new Notification.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容")
                    .setWhen(System.currentTimeMillis())
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            manager.notify(NOTIFICATION_ID, builder.build());
        } else {
            Random random = new Random();
            int smallIcon = random.nextInt(4) + 1;
            int icon = getResources().getIdentifier("b" + smallIcon, "drawable", getPackageName());
            

            Notification.Builder builder = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.b2)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容")
                    .setWhen(System.currentTimeMillis())
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            manager.notify(NOTIFICATION_ID, builder.build());
        }

        Toast.makeText(this, "通知已发送", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dialNumber("1234567890");
            } else {
                Toast.makeText(this, "拨号权限被拒绝", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(this)
                        .setTitle("需要拨号权限")
                        .setMessage("请前往设置开启拨号权限，以使用拨号功能")
                        .setPositiveButton("去设置", (dialog, which) -> {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void dialNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "未找到拨号应用", Toast.LENGTH_SHORT).show();
        }
    }
}
