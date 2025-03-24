package cn.nbmly.test0320;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tv2;
    Button btn;
    Button btn2;
    Button btn3;

    Button btn4;

    Button btn5;
    Button btn6;


    @SuppressLint("MissingInflatedId")
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
        tv2 = findViewById(R.id.textView2);
        tv2.setText("123");
        tv2.setTextColor(Color.GREEN);
        for (int i = 0; i < 10; i++) {
            tv2.append("123");
        }
        tv2.setTextSize(20);
        tv2.setText(R.string.love);
        tv2.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);

        btn = findViewById(R.id.button);
        btn.setText("Button1");
        btn.setOnClickListener(v -> btn.setTextColor(Color.RED));

        btn2 = findViewById(R.id.button2);
        btn2.setText("Button2");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2.setText("2");
            }
        });

        btn3 = findViewById(R.id.button3);
        btn3.setText("Button3");
        btn3.setOnClickListener(v -> btn3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50));

        btn4 = findViewById(R.id.button4);
        btn4.setText("Button4");
        btn4.setOnClickListener(new Nb());

        btn5 = findViewById(R.id.button5);
        btn5.setText("Button5");
        btn5.setOnClickListener(listener);

        btn6 = findViewById(R.id.button6);
        btn6.setText("Button6");
        btn6.setOnClickListener(v -> btn6.setTypeface(Typeface.MONOSPACE,Typeface.ITALIC));

    }
    //监听器对象
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btn5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 45);
            btn5.setTextColor(Color.YELLOW);
        }
    };

    //定义内部类
    private class Nb implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            btn4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            btn4.setTextColor(Color.BLUE);
        }
    }



}


