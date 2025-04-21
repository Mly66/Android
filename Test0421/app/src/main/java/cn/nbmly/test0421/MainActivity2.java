package cn.nbmly.test0421;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    ImageView imageView2;
    SeekBar seekBar;
    TextView textView;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageView2 = findViewById(R.id.imageView2);
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        imageView2.setImageResource(R.drawable.logo);
        Button button2 = findViewById(R.id.button2);
        seekBar.setMax(100);
        textView.setText("当前透明度：100%");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int alpha = progress * 255 / 100;
                imageView2.setImageAlpha(alpha);
                textView.setText("当前透明度：" + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        button2.setOnClickListener(v -> {
            new Thread(() -> {
                while (true) {
                    runOnUiThread(() -> {
                        int alpha = seekBar.getProgress();
                        alpha = (alpha + 1) % 101;
                        seekBar.setProgress(alpha);
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();
        });

    }
}