package cn.nbmly.test0612;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CalculateService calculateService;
    private boolean isBound = false;
    private TextView tvResult;
    private int firstNumber = 0;
    private String currentOperation = "";

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CalculateService.CalculateBinder binder = (CalculateService.CalculateBinder) service;
            calculateService = binder.getService();
            isBound = true;
            Toast.makeText(MainActivity.this, "服务已绑定", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            calculateService = null;
            isBound = false;
            Toast.makeText(MainActivity.this, "服务已解绑", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSub = findViewById(R.id.btnSub);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        Button btnCancel = findViewById(R.id.btnCancel);
        tvResult = findViewById(R.id.tvResult);

        // 加法按钮点击事件
        btnAdd.setOnClickListener(v -> {
            if (!isBound) {
                Intent intent = new Intent(this, CalculateService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
            firstNumber = 5; 
            currentOperation = "add";
            tvResult.setText("当前运算：加法\n第一个数：" + firstNumber + "\n请点击运算按钮进行 5 + 3 的计算");
        });

        // 减法按钮点击事件
        btnSub.setOnClickListener(v -> {
            if (!isBound) {
                Intent intent = new Intent(this, CalculateService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
            firstNumber = 5; 
            currentOperation = "sub";
            tvResult.setText("当前运算：减法\n第一个数：" + firstNumber + "\n请点击运算按钮进行 5 - 3 的计算");
        });

        // 乘法按钮点击事件
        btnMul.setOnClickListener(v -> {
            if (!isBound) {
                Intent intent = new Intent(this, CalculateService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
            firstNumber = 5; 
            currentOperation = "mul";
            tvResult.setText("当前运算：乘法\n第一个数：" + firstNumber + "\n请点击运算按钮进行 5 × 3 的计算");
        });

        // 除法按钮点击事件
        btnDiv.setOnClickListener(v -> {
            if (!isBound) {
                Intent intent = new Intent(this, CalculateService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
            firstNumber = 5; 
            currentOperation = "div";
            tvResult.setText("当前运算：除法\n第一个数：" + firstNumber + "\n请点击运算按钮进行 5 ÷ 3 的计算");
        });

        // 运算按钮点击事件
        btnCalculate.setOnClickListener(v -> {
            if (isBound && calculateService != null) {
                int secondNumber = 3; 
                int result = 0;
                String operationSymbol = "";

                switch (currentOperation) {
                    case "add":
                        result = calculateService.add(firstNumber, secondNumber);
                        operationSymbol = "+";
                        break;
                    case "sub":
                        result = calculateService.subtract(firstNumber, secondNumber);
                        operationSymbol = "-";
                        break;
                    case "mul":
                        result = calculateService.multiply(firstNumber, secondNumber);
                        operationSymbol = "×";
                        break;
                    case "div":
                        if (secondNumber != 0) {
                            result = calculateService.divide(firstNumber, secondNumber);
                            operationSymbol = "÷";
                        } else {
                            Toast.makeText(this, "除数不能为0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
                    default:
                        Toast.makeText(this, "请先选择运算类型", Toast.LENGTH_SHORT).show();
                        return;
                }

                String operationName = "";
                switch (currentOperation) {
                    case "add":
                        operationName = "加法";
                        break;
                    case "sub":
                        operationName = "减法";
                        break;
                    case "mul":
                        operationName = "乘法";
                        break;
                    case "div":
                        operationName = "除法";
                        break;
                }

                tvResult.setText("运算类型：" + operationName + "\n" +
                        "计算过程：" + firstNumber + " " + operationSymbol + " " + secondNumber + "\n" +
                        "计算结果：" + result);
                Toast.makeText(this, "计算结果: " + result, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "请先选择运算类型", Toast.LENGTH_SHORT).show();
            }
        });

        // 取消按钮点击事件
        btnCancel.setOnClickListener(v -> {
            if (isBound) {
                unbindService(connection);
                isBound = false;
                currentOperation = "";
                tvResult.setText("服务已解绑\n请选择运算类型");
                Toast.makeText(this, "服务已解绑", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "服务未绑定", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }
}
