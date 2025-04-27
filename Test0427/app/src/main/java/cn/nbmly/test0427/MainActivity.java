package cn.nbmly.test0427;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textPercentage;
    TextView textFraction;
    Button buttonAuto;
    Handler handler = new Handler(Looper.getMainLooper());

    @SuppressLint("SetTextI18n")
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

        progressBar = findViewById(R.id.progress_primary);
        textPercentage = findViewById(R.id.text_percentage);
        textFraction = findViewById(R.id.text_fraction);
        buttonAuto = findViewById(R.id.button_auto);

        updateText();

        buttonAuto.setOnClickListener(v -> {
            new Thread(() -> {
                while (progressBar.getProgress() < 100) {
                    handler.post(() -> {
                        int alpha = progressBar.getProgress();
                        alpha = (alpha + 1) % 101;
                        progressBar.setProgress(alpha);
                        updateText();
                    });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateText() {
        int progress = progressBar.getProgress();
        textPercentage.setText(progress + "%");
        textFraction.setText(progress + "/100");
    }
}
