package cn.nbmly.test0508;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private TextView tvName;
    private TextView tvPassword;
    private TextView tvGender;
    private Button btnConfirm;

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
        tvName = findViewById(R.id.tvName);
        tvPassword = findViewById(R.id.tvPassword);
        tvGender = findViewById(R.id.tvGender);
        btnConfirm = findViewById(R.id.btnConfirm);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");
        String gender = intent.getStringExtra("gender");
        tvName.setText(name);
        tvPassword.setText(password);
        tvGender.setText(gender);
        btnConfirm.setText("确认数据无误");
        btnConfirm.setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra("success", true);
            setResult(RESULT_OK, result);
            finish();
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent result = new Intent();
            result.putExtra("success", false);
            setResult(RESULT_OK, result);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}