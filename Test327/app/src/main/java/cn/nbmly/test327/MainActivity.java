package cn.nbmly.test327;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    CheckBox checkBox2;
    CheckBox checkBox3;

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
        textView = findViewById(R.id.textView);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (checkBox3.isChecked()) {
                        textView.setTypeface(null, Typeface.BOLD_ITALIC);
                    } else {
                        textView.setTypeface(null, Typeface.BOLD);
                    }

                } else {
                    if (checkBox3.isChecked()) {
                        textView.setTypeface(null, Typeface.ITALIC);
                    } else {
                        textView.setTypeface(null, Typeface.NORMAL);
                    }
                }
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (checkBox2.isChecked()) {
                        textView.setTypeface(null, Typeface.BOLD_ITALIC);
                    } else {
                        textView.setTypeface(null, Typeface.ITALIC);
                    }
                } else {
                    if (checkBox2.isChecked()) {
                        textView.setTypeface(null, Typeface.BOLD);
                    } else {
                        textView.setTypeface(null, Typeface.NORMAL);
                    }
                }
            }
        });

    }

}