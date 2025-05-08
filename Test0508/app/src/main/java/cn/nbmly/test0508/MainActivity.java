package cn.nbmly.test0508;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE = 1;
    private EditText name;
    private EditText password;
    private RadioGroup rgGender;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private Button btnSubmit;

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
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("password", password.getText().toString());
            intent.putExtra("gender", rbMale.isChecked() ? "男" : "女");
            startActivityForResult(intent, REQ_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                boolean success = data.getBooleanExtra("success", false);
                name.setText("");
                password.setText("");
                rgGender.clearCheck();
                Toast.makeText(this, success ? "注册成功" : "注册失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}