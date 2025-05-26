package cn.nbmly.test0526;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnCreateDatabase;
    private Button btnCreateTable;
    private Button btnInitData;
    private Button btnExecuteQuery;
    private Button btnInsertUser;
    private Button btnUpdateUser;
    private Button btnDeleteUser;
    private Button btnQueryUser;
    private EditText etSqlQuery;
    private EditText etUserId;
    private EditText etName;
    private EditText etAge;
    private EditText etEmail;
    private TextView tvQueryResult;

    private MyDataHelper dbHelper;
    private SQLiteDatabase db;
    private UserDao userDao;

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

        dbHelper = new MyDataHelper(this);
        userDao = new UserDao(this);

        btnCreateDatabase = findViewById(R.id.btnCreateDatabase);
        btnCreateTable = findViewById(R.id.btnCreateTable);
        btnInitData = findViewById(R.id.btnInitData);
        btnExecuteQuery = findViewById(R.id.btnExecuteQuery);

        btnInsertUser = findViewById(R.id.btnInsertUser);
        btnUpdateUser = findViewById(R.id.btnUpdateUser);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);
        btnQueryUser = findViewById(R.id.btnQueryUser);

        etSqlQuery = findViewById(R.id.etSqlQuery);
        etUserId = findViewById(R.id.etUserId);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etEmail = findViewById(R.id.etEmail);
        tvQueryResult = findViewById(R.id.tvQueryResult);

        btnCreateDatabase.setOnClickListener(v -> {
            db = dbHelper.getWritableDatabase();
            Toast.makeText(this, "数据库创建成功", Toast.LENGTH_SHORT).show();
        });

        btnCreateTable.setOnClickListener(v -> {
            db = dbHelper.getWritableDatabase();
            Toast.makeText(this, "数据表创建成功", Toast.LENGTH_SHORT).show();
        });

        btnInitData.setOnClickListener(v -> {
            try {
                db = dbHelper.getWritableDatabase();
                db.execSQL("DELETE FROM users");
                String[] initDataQueries = {
                        "INSERT INTO users (name, age, email) VALUES ('张三', 25, 'zhangsan@example.com')",
                        "INSERT INTO users (name, age, email) VALUES ('李四', 30, 'lisi@example.com')",
                        "INSERT INTO users (name, age, email) VALUES ('王五', 22, 'wangwu@example.com')"
                };

                for (String query : initDataQueries) {
                    db.execSQL(query);
                }

                Toast.makeText(this, "初始化3条示例数据成功", Toast.LENGTH_SHORT).show();
                displayAllUsers();
            } catch (Exception e) {
                tvQueryResult.setText("错误：" + e.getMessage());
                Toast.makeText(this, "初始化数据失败", Toast.LENGTH_SHORT).show();
            } finally {
                if (db != null && db.isOpen()) {
                    db.close();
                }
            }
        });

        btnInsertUser.setOnClickListener(v -> {
            try {
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                String email = etEmail.getText().toString();

                long newRowId = userDao.insertUser(name, age, email);
                Toast.makeText(this, "插入用户成功，ID: " + newRowId, Toast.LENGTH_SHORT).show();

                displayAllUsers();
            } catch (Exception e) {
                tvQueryResult.setText("错误：" + e.getMessage());
                Toast.makeText(this, "插入用户失败", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdateUser.setOnClickListener(v -> {
            try {
                int userId = Integer.parseInt(etUserId.getText().toString());
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                String email = etEmail.getText().toString();

                int updatedRows = userDao.updateUser(userId, name, age, email);
                Toast.makeText(this, "更新用户成功，影响行数：" + updatedRows, Toast.LENGTH_SHORT).show();

                displayAllUsers();
            } catch (Exception e) {
                tvQueryResult.setText("错误：" + e.getMessage());
                Toast.makeText(this, "更新用户失败", Toast.LENGTH_SHORT).show();
            }
        });

        btnDeleteUser.setOnClickListener(v -> {
            try {
                int userId = Integer.parseInt(etUserId.getText().toString());
                int deletedRows = userDao.deleteUser(userId);
                Toast.makeText(this, "删除用户成功，影响行数：" + deletedRows, Toast.LENGTH_SHORT).show();

                displayAllUsers();
            } catch (Exception e) {
                tvQueryResult.setText("错误：" + e.getMessage());
                Toast.makeText(this, "删除用户失败", Toast.LENGTH_SHORT).show();
            }
        });

        btnQueryUser.setOnClickListener(v -> {
            try {
                // 尝试按ID查询
                String userIdStr = etUserId.getText().toString();
                if (!userIdStr.isEmpty()) {
                    int userId = Integer.parseInt(userIdStr);
                    User user = userDao.getUserById(userId);

                    if (user != null) {
                        tvQueryResult.setText("查询结果：\n" + user.toString());
                    } else {
                        tvQueryResult.setText("未找到用户");
                    }
                } else {
                    // 如果没有输入ID，查询所有用户
                    displayAllUsers();
                }
            } catch (Exception e) {
                tvQueryResult.setText("错误：" + e.getMessage());
                Toast.makeText(this, "查询用户失败", Toast.LENGTH_SHORT).show();
            }
        });

        btnExecuteQuery.setOnClickListener(v -> {
            String sqlQuery = etSqlQuery.getText().toString().trim();
            if (sqlQuery.isEmpty()) {
                Toast.makeText(this, "请输入SQL语句", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                db = dbHelper.getWritableDatabase();

                if (sqlQuery.toUpperCase().startsWith("SELECT")) {
                    Cursor cursor = db.rawQuery(sqlQuery, null);
                    List<String> results = new ArrayList<>();


                    String[] columnNames = cursor.getColumnNames();

                    StringBuilder headerBuilder = new StringBuilder();
                    for (String columnName : columnNames) {
                        headerBuilder.append(columnName).append("\t");
                    }
                    results.add(headerBuilder.toString());

                    while (cursor.moveToNext()) {
                        StringBuilder rowBuilder = new StringBuilder();
                        for (String columnName : columnNames) {
                            int columnIndex = cursor.getColumnIndex(columnName);
                            rowBuilder.append(cursor.getString(columnIndex)).append("\t");
                        }
                        results.add(rowBuilder.toString());
                    }

                    cursor.close();

                    tvQueryResult.setText(String.join("\n", results));
                } else {

                    db.execSQL(sqlQuery);
                    tvQueryResult.setText("执行成功：" + sqlQuery);

                    if (sqlQuery.toUpperCase().contains("USERS")) {
                        displayAllUsers();
                    }
                }

                Toast.makeText(this, "SQL语句执行成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                tvQueryResult.setText("错误：" + e.getMessage());
                Toast.makeText(this, "SQL语句执行失败", Toast.LENGTH_SHORT).show();
            } finally {
                if (db != null && db.isOpen()) {
                    db.close();
                }
            }
        });
    }

    private void displayAllUsers() {
        List<User> users = userDao.getAllUsers();
        if (users.isEmpty()) {
            tvQueryResult.setText("没有用户数据");
        } else {
            StringBuilder result = new StringBuilder("所有用户：\n");
            for (User user : users) {
                result.append(user.toString()).append("\n");
            }
            tvQueryResult.setText(result.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
