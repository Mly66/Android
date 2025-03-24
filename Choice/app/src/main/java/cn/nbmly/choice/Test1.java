package cn.nbmly.choice;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Test1 extends AppCompatActivity {
    TextView textView3;
    RadioGroup radioGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView3 = findViewById(R.id.textView3);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup2.setOnCheckedChangeListener((group, checkedId) -> {
            for (int i = 0; i < group.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) group.getChildAt(i);
                if (radioButton.getId() == checkedId) {
                    radioButton.setBackgroundColor(Color.BLUE);
                    textView3.setText(radioButton.getText());
                    textView3.setTextColor(Color.WHITE);
                    textView3.setTextSize(24);
                    textView3.setBackgroundColor(Color.GREEN);
                } else {
                    radioButton.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
    }
}
