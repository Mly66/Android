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
    }

    public MySearch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.search, null);
        Button button5 = view.findViewById(R.id.button5);
        EditText editText = view.findViewById(R.id.editTextText);
        button5.setOnClickListener(v -> {
            String data = editText.getText().toString();
            if (TextUtils.isEmpty(data)) {
                Toast.makeText(context, "empty", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "null", Toast.LENGTH_LONG).show();
            }
        });

    }

    public MySearch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MySearch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
