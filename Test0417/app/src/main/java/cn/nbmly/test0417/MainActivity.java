package cn.nbmly.test0417;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {
    ViewPager2 VIP;
    int[] images = {R.drawable.background_02_summer, R.drawable.background_02_spring, R.drawable.background_02_autumn,
            R.drawable.background_02_winter};
    private ImageView[] indicators;

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
        indicators = new ImageView[4];
        indicators[0] = findViewById(R.id.indicator1);
        indicators[1] = findViewById(R.id.indicator2);
        indicators[2] = findViewById(R.id.indicator3);
        indicators[3] = findViewById(R.id.indicator4);

        VIP = findViewById(R.id.VIP);
        VIP.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        VIP.setAdapter(adapter);
        VIP.setOffscreenPageLimit(images.length);


        VIP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (ImageView indicator : indicators) {
                    indicator.setBackgroundResource(R.drawable.circle);
                }

                if (position == 0) {
                    indicators[0].setBackgroundResource(R.drawable.rounded_rectangle);
                }
                if (position != 0) {
                    indicators[position].setBackgroundResource(R.drawable.indicator_selected);
                }
            }
        });
    }

    class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout1, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.imageView.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {
            return images.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }
}