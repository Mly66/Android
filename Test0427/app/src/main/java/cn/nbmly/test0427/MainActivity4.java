package cn.nbmly.test0427;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity4 extends AppCompatActivity {
    private Button btnConfirm, btnList, btnSingleChoice, btnMultiChoice, btnInput, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnConfirm = findViewById(R.id.btn_confirm);
        btnList = findViewById(R.id.btn_list);
        btnSingleChoice = findViewById(R.id.btn_single_choice);
        btnMultiChoice = findViewById(R.id.btn_multi_choice);
        btnInput = findViewById(R.id.btn_input);
        btnLogin = findViewById(R.id.btn_login);

        btnConfirm.setOnClickListener(v -> showConfirmDialog());
        btnList.setOnClickListener(v -> showListDialog());
        btnSingleChoice.setOnClickListener(v -> showSingleChoiceDialog());
        btnMultiChoice.setOnClickListener(v -> showMultiChoiceDialog());
        btnInput.setOnClickListener(v -> showInputDialog());
        btnLogin.setOnClickListener(v -> showLoginDialog());


    }

    public void showConfirmDialog() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("确定要执行此操作吗？")
                .setPositiveButton("确定", (dialog, which) -> {
                    Toast.makeText(this, "点击了确定", Toast.LENGTH_SHORT).show();
                    //finish();

                }).setNegativeButton("取消", null).show();
    }

    public void showListDialog() {
        new AlertDialog.Builder(this)
                .setTitle("选择你所在的城市")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setItems(new String[]{"北京", "上海", "广州"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            btnList.setText("北京");
                            Toast.makeText(this, "您点击了：北京", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：北京");
                            break;
                        case 1:
                            btnList.setText("上海");
                            Toast.makeText(this, "您点击了：上海", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：上海");
                            break;
                        case 2:
                            btnList.setText("广州");
                            Toast.makeText(this, "您点击了：广州", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：广州");
                            break;
                        default:
                            Toast.makeText(this, "未知选项：" + which, Toast.LENGTH_SHORT).show();
                            Log.w("MainActivity4", "未知选项：" + which);
                            break;
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    public void showSingleChoiceDialog() {
        new AlertDialog.Builder(this)
                .setTitle("选择一个选项")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", (dialog, which) -> {
                    Toast.makeText(this, "点击了确定", Toast.LENGTH_SHORT).show();
                    //finish();

                })
                .setSingleChoiceItems(new String[]{"红色", "绿色", "蓝色"}, 0, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            btnSingleChoice.setBackgroundColor(Color.RED);
                            Toast.makeText(this, "您点击了：红色", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：红色");
                            break;
                        case 1:
                            btnSingleChoice.setBackgroundColor(Color.GREEN);
                            Toast.makeText(this, "您点击了：绿色", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：绿色");
                            break;
                        case 2:
                            btnSingleChoice.setBackgroundColor(Color.BLUE);
                            Toast.makeText(this, "您点击了：蓝色", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：蓝色");
                            break;
                        default:
                            Toast.makeText(this, "未知选项：" + which, Toast.LENGTH_SHORT).show();
                            Log.w("MainActivity4", "未知选项：" + which);
                            break;
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    public void showMultiChoiceDialog() {
        String[] subjects = new String[]{"物理", "化学", "生物", "历史", "政治", "地理"};
        boolean[] checkedItems = new boolean[subjects.length];

        new AlertDialog.Builder(this)
                .setTitle("选择你高中选科")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setMultiChoiceItems(subjects, checkedItems, (dialog, which, isChecked) -> {
                    checkedItems[which] = isChecked;
                })
                .setPositiveButton("确定", (dialog, which) -> {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < subjects.length; i++) {
                        if (checkedItems[i]) {
                            sb.append(subjects[i]).append(" ");
                        }
                    }
                    String result = sb.toString().trim();
                    btnMultiChoice.setText(result);
                    Toast.makeText(this, "您选择了：" + result, Toast.LENGTH_SHORT).show();
                    Log.i("MainActivity4", "您选择了：" + result);
                })
                .setNegativeButton("取消", null)
                .show();
    }


    public void showInputDialog() {
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_input, null);
        EditText editText = dialogView.findViewById(R.id.editText);

        new AlertDialog.Builder(this)
                .setTitle("输入内容")
                .setIcon(R.mipmap.ic_launcher)
                .setView(dialogView)
                .setPositiveButton("确定", (dialog, which) -> {
                    String inputText = editText.getText().toString();
                    Toast.makeText(this, "您输入了：" + inputText, Toast.LENGTH_SHORT).show();
                    Log.i("MainActivity4", "您输入了：" + inputText);
                    btnInput.setText(inputText);
                })
                .setNegativeButton("取消", null)
                .show();
    }

    public void showLoginDialog() {
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_login, null);
        EditText etUsername = dialogView.findViewById(R.id.etUsername);
        EditText etPassword = dialogView.findViewById(R.id.etPassword);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button Login = dialogView.findViewById(R.id.Login);
        Login.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            } else if (username.equals("admin") && password.equals("123456")) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        });

        new AlertDialog.Builder(this)
                .setTitle("请输入账号密码")
                .setIcon(R.mipmap.ic_launcher_round)
                .setView(dialogView)
                .setPositiveButton("登录", (dialog, which) -> {
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    Toast.makeText(this, "用户名：" + username + '\n' + "密码：" + password, Toast.LENGTH_SHORT).show();

                })
                .setNegativeButton("取消", null)
                .show();
    }


}