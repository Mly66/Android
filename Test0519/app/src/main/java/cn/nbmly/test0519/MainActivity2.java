package cn.nbmly.test0519;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity2 extends AppCompatActivity {
    private Button btn_write;
    private Button btn_read;
    private Button btn_delete;
    private CheckBox cb_remember_username;
    private CheckBox cb_remember_password;
    private TextInputEditText et_username;
    private TextInputEditText et_password;

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
        btn_write = findViewById(R.id.btn_write);
        btn_read = findViewById(R.id.btn_read);
        btn_delete = findViewById(R.id.btn_delete);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        cb_remember_username = findViewById(R.id.cb_remember_username);
        cb_remember_password = findViewById(R.id.cb_remember_password);
        initData();
        btn_write.setOnClickListener(v -> {
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();
            if (!cb_remember_username.isChecked()) {
                username = "";
            }
            if (!cb_remember_password.isChecked()) {
                password = "";
            }

            SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();
            Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
        });

        btn_read.setOnClickListener(v -> {
            SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
            et_username.setText(sp.getString("username", ""));
            et_password.setText(sp.getString("password", ""));
            Toast.makeText(this, "读取成功", Toast.LENGTH_SHORT).show();
        });

        btn_delete.setOnClickListener(v -> {
            SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.apply();
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            et_username.setText("");
            et_password.setText("");
        });

    }
    public void initData(){
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        et_username.setText(sp.getString("username", ""));
        et_password.setText(sp.getString("password", ""));


    }
}