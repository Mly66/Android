package cn.nbmly.testservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button switchLight;
    private View lightView;
    private boolean isLightOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 启动 Service
        startService(new Intent(this, MyService.class));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        switchLight = findViewById(R.id.switchLight);
        lightView = findViewById(R.id.lightView);

        switchLight.setOnClickListener(v -> {
            if (isLightOn) {
                lightView.setBackgroundResource(R.drawable.circle_shape);
                switchLight.setText("开灯");
                isLightOn = false;
            } else {
                lightView.setBackgroundResource(R.drawable.circle_shape_on);
                switchLight.setText("关灯");
                isLightOn = true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 停止 Service
        stopService(new Intent(this, MyService.class));
    }
}
