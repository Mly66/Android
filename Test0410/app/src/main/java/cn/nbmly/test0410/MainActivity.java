package cn.nbmly.test0410;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private HomeAdapter mAdapter;

    //    private String[] names = {"cat", "dog", "duck", "tiger"};
//    private int[] imgs = {R.drawable.cat, R.drawable.dog, R.drawable.duck, R.drawable.tiger};
//    private String[] introduce = {"catcat", "dogdog", "duckduck", "tigertiger"};
    private int[] imgs = {R.drawable.bk1, R.drawable.bk2, R.drawable.bk3, R.drawable.bk4, R.drawable.bk5, R.drawable.bk6, R.drawable.bk7, R.drawable.bk8, R.drawable.bk9};
    private String[] books = {"计算机网络", "计算机信息", "计算机英语", "计算机文化基础", "计算机组成原理", "计算机网络", "计算机信息", "计算机英语", "计算机文化基础"};
    private String[] names = {"nbmly","nbmly","nbmly","nbmly","nbmly","nbmly","nbmly","nbmly","nbmly"};

    private String[] introduce = {"黑马出版社","黑马出版社","黑马出版社","黑马出版社","黑马出版社","黑马出版社","黑马出版社","黑马出版社","黑马出版社"};

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
            holder.nameTextView.setText(names[position]);
            holder.bookTextView.setText(books[position]);
            holder.introTextView.setText(introduce[position]);
            holder.iconImageView.setImageResource(imgs[position]);
        }

        @Override
        public int getItemCount() {
            return names.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView nameTextView;
            TextView bookTextView;
            TextView introTextView;
            ImageView iconImageView;

            ViewHolder(View itemView) {
                super(itemView);
                nameTextView = itemView.findViewById(R.id.name);
                bookTextView = itemView.findViewById(R.id.book);
                introTextView = itemView.findViewById(R.id.intro);
                iconImageView = itemView.findViewById(R.id.item_img);
            }
        }
    }
}
