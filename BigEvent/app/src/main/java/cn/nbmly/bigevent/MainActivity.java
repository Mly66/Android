package cn.nbmly.bigevent;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

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

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsAdapter = new NewsAdapter(this);
        recyclerView.setAdapter(newsAdapter);


        loadTestData();
    }

    private void loadTestData() {
        List<NewsItem> testData = new ArrayList<>();

        NewsItem item1 = new NewsItem("这是没有图片的新闻标题", "用户A", 10, "5分钟前");

        testData.add(item1);

        NewsItem item2 = new NewsItem("这是单张图片的新闻，显示在右侧", "用户B", 25, "30分钟前");
        item2.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        testData.add(item2);

        NewsItem item3 = new NewsItem("这是两张图片的新闻，横向排列在标题下方", "用户C", 8, "1小时前");
        item3.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        item3.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        testData.add(item3);

        NewsItem item4 = new NewsItem("这是三张图片的新闻，横向排列在标题下方", "用户D", 42, "2小时前");
        item4.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        item4.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        item4.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        testData.add(item4);

        NewsItem item5 = new NewsItem("这是四张图片的新闻，2x2网格布局", "用户E", 15, "3小时前");
        item5.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        item5.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        item5.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        item5.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        testData.add(item5);

        NewsItem item6 = new NewsItem("这是六张图片的新闻，一行三个布局", "用户F", 63, "5小时前");
        for (int i = 1; i <= 6; i++) {
            item6.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        }
        testData.add(item6);

        NewsItem item7 = new NewsItem("这是九张图片的新闻，展示最大数量", "用户G", 37, "1天前");
        for (int i = 1; i <= 9; i++) {
            item7.addImageUrl("https://free-img.mofashi.ltd/5/2025/03/17/67d7c20cd1292.png");
        }
        testData.add(item7);

        newsAdapter.setData(testData);
    }

}