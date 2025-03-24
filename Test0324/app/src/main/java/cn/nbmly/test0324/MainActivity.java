package cn.nbmly.test0324;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;
    Button bt5;
    Button bt6;
    TextView txv1;

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
        bt1 = findViewById(R.id.button);
        bt2 = findViewById(R.id.button2);
        bt3 = findViewById(R.id.button3);
        bt4 = findViewById(R.id.button4);
        bt5 = findViewById(R.id.button5);
        bt6 = findViewById(R.id.button6);
        txv1 = findViewById(R.id.textView);
        bt1.setOnClickListener(v -> {
                    bt1.setBackgroundColor(0xFFFF0000);
                    txv1.setTextColor(0xFFFF0000);
                }
        );
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt2.setBackgroundColor(Color.YELLOW);
                txv1.setTextColor(Color.YELLOW);
            }
        });
        bt3.setOnClickListener(new onTest());
        bt4.setOnClickListener(testBt4);
        bt5.setOnClickListener(new BtnClick());
        bt6.setOnClickListener(new BtnClick());
    }

    private class onTest implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            bt3.setBackgroundColor(Color.BLUE);
            txv1.setTextColor(Color.BLUE);
        }
    }

    View.OnClickListener testBt4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bt4.setBackgroundColor(Color.GREEN);
            txv1.setTextColor(Color.GREEN);
        }
    };

    class BtnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button5) {
                bt5.setBackgroundColor(Color.BLACK);
                txv1.setTextColor(Color.BLACK);
            }
            else {
                v.setBackgroundColor(Color.WHITE);
                v.setBackgroundColor(Color.DKGRAY);
                txv1.setTextColor(Color.WHITE);
            }

        }
    }


}