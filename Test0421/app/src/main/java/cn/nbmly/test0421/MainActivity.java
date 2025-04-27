package cn.nbmly.test0421;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button1, button2;
    ProgressBar progressBar;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NotNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                progressBar.setProgress(5);
                button1.setText("暂停");
            }
        }
    };


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

        imageView = findViewById(R.id.imageView);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button3);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setProgress(0);

        AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
        drawable.start();

        button1.setOnClickListener(v -> {
//            if (progressBar.getProgress() < 100) {
//                progressBar.incrementProgressBy(5);
//            } else {
//                Toast.makeText(MainActivity.this, "已完成", Toast.LENGTH_SHORT).show();
//            }
            new Thread(() -> {
                Message msg = handler.obtainMessage();
                msg.what = 1;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });

        button2.setOnClickListener(v -> {
            //安卓子线程不能修改UI
            new Thread(() -> {
                while (progressBar.getProgress() < 100) {
                    progressBar.post(() -> progressBar.incrementProgressBy(5));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });
    }
}
