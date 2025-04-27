package cn.nbmly.test0421;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.annotation.Nullable;

public class MySearch extends LinearLayout {
    public MySearch(Context context) {
        super(context);
        init(context);
    }

    public MySearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MySearch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.search, true);
        Button button5 = findViewById(R.id.button5);
        EditText editText = findViewById(R.id.editTextText);
        button5.setOnClickListener(v -> {
            String data = editText.getText().toString().trim();
            if (TextUtils.isEmpty(data)) {
                Toast.makeText(context, "请输入要搜索的内容", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "搜索: " + data, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inflate(Context context, int search, boolean b) {
        LayoutInflater.from(context).inflate(search, this, b);

    }

}
