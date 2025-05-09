package cn.nbmly.test0414;

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
    String[] fruitname = {"apple", "banana", "orange", "watermelon", "pear", "grape", "pineapple", "strawberry", "cherry", "mango"};
    int[] fruitpic = {
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

            holder.fruitTextView.setText(fruitname[position]);
            holder.iconImageView.setImageResource(fruitpic[position]);
        }

        @Override
        public int getItemCount() {
            return fruitname.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView fruitTextView;
            ImageView iconImageView;

            ViewHolder(View itemView) {
                super(itemView);
                fruitTextView = itemView.findViewById(R.id.fruit);
                iconImageView = itemView.findViewById(R.id.item_img);
            }
        }
    }
}