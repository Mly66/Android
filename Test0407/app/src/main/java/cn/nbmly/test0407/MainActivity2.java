package cn.nbmly.test0407;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ListView lv2;
    List<User> list = new ArrayList<>();

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

        lv2 = findViewById(R.id.lv2);
        initData();

        lv2.setAdapter(baseAdapter);
        lv2.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(MainActivity2.this, "点击了：" + list.get(position).getName(), Toast.LENGTH_SHORT).show()
        );
    }

    // 数据适配器
    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(MainActivity2.this).inflate(R.layout.list_item2, parent, false);
            ImageView item_img = view.findViewById(R.id.item_img);
            TextView item_text = view.findViewById(R.id.item_text);

            User user = list.get(position);
            item_img.setImageResource(user.getImgResId());
            item_text.setText(user.getName());

            return view;
        }
    };

    private void initData() {
        list.add(new User("数据1", R.drawable.test1));
        list.add(new User("数据2", R.drawable.test2));
        list.add(new User("数据3", R.drawable.test3));
        list.add(new User("数据4", R.drawable.test4));
        list.add(new User("数据5", R.drawable.test5));
        list.add(new User("数据6", R.drawable.test6));
        list.add(new User("数据7", R.drawable.test7));
        list.add(new User("数据8", R.drawable.test8));
        list.add(new User("数据1", R.drawable.test1));
        list.add(new User("数据2", R.drawable.test2));
        list.add(new User("数据3", R.drawable.test3));
        list.add(new User("数据4", R.drawable.test4));
        list.add(new User("数据5", R.drawable.test5));
        list.add(new User("数据6", R.drawable.test6));
        list.add(new User("数据7", R.drawable.test7));
        list.add(new User("数据8", R.drawable.test8));
    }
}
