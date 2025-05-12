package cn.nbmly.test0512;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REGISTER_REQUEST_CODE = 1;
    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister, btnExit, btnCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets sb = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sb.left, sb.top, sb.right, sb.bottom);
            return insets;
        });

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin    = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnExit     = findViewById(R.id.btnExit);
        btnCity     = findViewById(R.id.btnCity);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity3.class);
            intent.putExtra("username", etUsername.getText().toString());
            intent.putExtra("password", etPassword.getText().toString());
            startActivity(intent);
        });
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivityForResult(intent, REGISTER_REQUEST_CODE);
        });
        btnExit.setOnClickListener(v -> finish());
        btnCity.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity4.class);
            startActivityForResult(intent, REGISTER_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTER_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            etUsername.setText(data.getStringExtra("username"));
            String city = data.getStringExtra("selectedCity");
            Toast.makeText(this, city, Toast.LENGTH_SHORT).show();
        }
    }
}
