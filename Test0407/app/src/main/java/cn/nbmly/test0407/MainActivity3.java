package cn.nbmly.test0407;

import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    ListView lvgood;
    List<goods> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lvgood = findViewById(R.id.lvgood);
        lvgood.setAdapter(baseAdapter);
        initData();
        lvgood.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(MainActivity3.this, "点击了：" + list.get(position).getName(), Toast.LENGTH_SHORT).show()
        );


    }

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
//            View view = LayoutInflater.from(MainActivity3.this).inflate(R.layout.list_item3, parent, false);
//            ImageView item_img = view.findViewById(R.id.item_img);
//            TextView name = view.findViewById(R.id.name);
//            TextView price = view.findViewById(R.id.price);
//            name.setText(list.get(position).getName());
//            price.setText(String.valueOf(list.get(position).getPrice()));
//            item_img.setImageResource(list.get(position).getImgUrl());
//            return view;
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(MainActivity3.this, R.layout.list_item3, null);
                holder = new ViewHolder();
                holder.name = convertView.findViewById(R.id.name);
                holder.price = convertView.findViewById(R.id.price);
                holder.iv = convertView.findViewById(R.id.item_img);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.name.setText(list.get(position).getName());
            holder.price.setText(String.valueOf(list.get(position).getPrice()));
            holder.iv.setImageResource(list.get(position).getImgUrl());
            return convertView;
        }
    };

    void initData() {
        list.add(new goods("数据1", R.drawable.test1, 100));
        list.add(new goods("数据2", R.drawable.test2, 200));
        list.add(new goods("数据3", R.drawable.test3, 300));
        list.add(new goods("数据4", R.drawable.test4, 400));
        list.add(new goods("数据5", R.drawable.test5, 500));
        list.add(new goods("数据6", R.drawable.test6, 600));
        list.add(new goods("数据7", R.drawable.test7, 700));
        list.add(new goods("数据8", R.drawable.test8, 800));
        list.add(new goods("数据1", R.drawable.test1, 100));
        list.add(new goods("数据2", R.drawable.test2, 200));
        list.add(new goods("数据3", R.drawable.test3, 300));
        list.add(new goods("数据4", R.drawable.test4, 400));
        list.add(new goods("数据5", R.drawable.test5, 500));
        list.add(new goods("数据6", R.drawable.test6, 600));
        list.add(new goods("数据7", R.drawable.test7, 700));
        list.add(new goods("数据8", R.drawable.test8, 800));

    }
}