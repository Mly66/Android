package cn.nbmly.test0331;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    Button post;
    EditText name;
    EditText email;
    EditText password;
    RadioGroup sex;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;

    private static final String TAG = "RegisterInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        post = findViewById(R.id.button5);
        name = findViewById(R.id.editTextText);
        email = findViewById(R.id.editTextText3);
        password = findViewById(R.id.editTextText4);
        sex = findViewById(R.id.RadioGroup);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);

        post.setOnClickListener(v -> {
            String userName = name.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();

            int selectedSexId = sex.getCheckedRadioButtonId();
            String userSex = "未选择";
            if (selectedSexId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedSexId);
                userSex = selectedRadioButton.getText().toString();
            }

            StringBuilder hobbies = new StringBuilder();
            if (checkBox1.isChecked()) hobbies.append(checkBox1.getText()).append(" ");
            if (checkBox2.isChecked()) hobbies.append(checkBox2.getText()).append(" ");
            if (checkBox3.isChecked()) hobbies.append(checkBox3.getText()).append(" ");

            String registerInfo = "姓名: " + userName + "\n"
                    + "邮箱: " + userEmail + "\n"
                    + "密码: " + userPassword + "\n"
                    + "性别: " + userSex + "\n"
                    + "爱好: " + (hobbies.length() > 0 ? hobbies.toString().trim() : "无");
            Log.d(TAG, registerInfo);
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        });
    }
}
