package cn.nbmly.test0424;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        MyView myView = findViewById(R.id.my_custom_view);
        myView.color=0xff2386B6;
        myView.text="自定义视图";
        myView.size=24;
        myView.content="这是一个自定义视图示例";
        // 设置自定义视图的属性
        myView.paint.setColor(myView.color);
        myView.paint.setTextSize(myView.size);
        // 重新绘制视图
        myView.invalidate();


    }
}