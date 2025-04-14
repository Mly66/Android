package cn.nbmly.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private HomeAdapter mAdapter;
    String[] news_titles = {"突发：大地震袭击城市", "科技巨头发布新款智能手机", "本地球队夺得冠军", "新研究揭示茶的健康益处", "明星情侣宣布订婚", "股市创历史新高", "太空探测器发现新行星", "艺术展览吸引大量观众", "野生动物保护取得成效", "历史性条约签署"};
    String[] user_names = {"张三", "李四", "王五", "赵六", "周七", "钱八", "孙九", "吴十", "郑十一", "冯十二"};
    String[] event_nums = {"1评", "88评", "5评", "42评", "0评", "120评", "33评", "19评", "7评", "65评"};
    String[] publish_times = {"刚刚", "2分钟前", "30分钟前", "1小时前", "3小时前", "昨天", "2天前", "1周前", "2周前", "1个月前"};

    int[] right_images = {
            R.drawable.apple_pic,
            R.drawable.banana_pic,
            R.drawable.orange_pic,
            R.drawable.watermelon_pic,
            R.drawable.pear_pic,
            R.drawable.grape_pic,
            R.drawable.pineapple_pic,
            R.drawable.strawberry_pic,
            R.drawable.cherry_pic,
            R.drawable.mango_pic
    };

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
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeAdapter();
        rv.setAdapter(mAdapter);
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
        @NonNull
        @Override
        public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rv, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
            holder.news_title.setText(news_titles[position]);
            holder.user_name.setText(user_names[position]);
            holder.event_num.setText(event_nums[position]);
            holder.publish_time.setText(publish_times[position]);
            holder.right_image.setImageResource(right_images[position]);
        }

        @Override
        public int getItemCount() {
            return news_titles.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView news_title;
            TextView user_name;
            TextView event_num;
            TextView publish_time;
            ImageView right_image;

            ViewHolder(View itemView) {
                super(itemView);
                news_title = itemView.findViewById(R.id.news_title);
                user_name = itemView.findViewById(R.id.user_name);
                event_num = itemView.findViewById(R.id.event_num);
                publish_time = itemView.findViewById(R.id.publish_time);
                right_image = itemView.findViewById(R.id.right_image);
            }
        }
    }
}