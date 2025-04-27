package cn.nbmly.test0427;

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
    private Button btnConfirm, btnList, btnSingleChoice, btnMultiChoice, btnInput;

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
        btnConfirm.setOnClickListener(v -> showConfirmDialog());
        btnList.setOnClickListener(v -> showListDialog());
        btnSingleChoice.setOnClickListener(v -> showSingleChoiceDialog());
        btnMultiChoice.setOnClickListener(v -> showMultiChoiceDialog());
        btnInput.setOnClickListener(v -> showInputDialog());


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
                .setTitle("选择一个选项")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", (dialog, which) -> {
                    Toast.makeText(this, "点击了确定", Toast.LENGTH_SHORT).show();
                    //finish();

                })
                .setItems(new String[]{"选项1", "选项2", "选项3"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            Toast.makeText(this, "您点击了：选项1", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：选项1");
                            break;
                        case 1:
                            Toast.makeText(this, "您点击了：选项2", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：选项2");
                            break;
                        case 2:
                            Toast.makeText(this, "您点击了：选项3", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：选项3");
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
                .setSingleChoiceItems(new String[]{"选项1", "选项2", "选项3"}, 0, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            Toast.makeText(this, "您点击了：选项1", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：选项1");
                            break;
                        case 1:
                            Toast.makeText(this, "您点击了：选项2", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：选项2");
                            break;
                        case 2:
                            Toast.makeText(this, "您点击了：选项3", Toast.LENGTH_SHORT).show();
                            Log.i("MainActivity4", "您点击了：选项3");
                            break;
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    public void showMultiChoiceDialog() {
        boolean[] checkedItems = new boolean[]{false, false, false};
        new AlertDialog.Builder(this)
                .setTitle("选择多个选项")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setMultiChoiceItems(new String[]{"选项1", "选项2", "选项3"}, checkedItems, (dialog, which, isChecked) -> {
                    checkedItems[which] = isChecked;
                })
                .setPositiveButton("确定", (dialog, which) -> {
                    StringBuilder selectedItems = new StringBuilder();
                    for (int i = 0; i < checkedItems.length; i++) {
                        if (checkedItems[i]) {
                            selectedItems.append(i + 1).append(" ");
                        }
                    }
                    Toast.makeText(this, "您选择了：" + selectedItems.toString(), Toast.LENGTH_SHORT).show();
                    Log.i("MainActivity4", "您选择了：" + selectedItems.toString());
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
                })
                .setNegativeButton("取消", null)
                .show();
    }



}