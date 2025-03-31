package cn.nbmly.test0331;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button bt1;
    Button bt2;
    ImageView imageView;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    int image[] = {R.drawable.test1, R.drawable.test2, R.drawable.test3, R.drawable.test4, R.drawable.test5,
            R.drawable.test6, R.drawable.test7, R.drawable.test8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        bt1.setOnClickListener(v -> imageView.setImageResource(R.drawable.test1));
        bt2.setOnClickListener(v -> imageView.setImageResource(R.drawable.test2));
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int currentIndex = 0;
        for (int i = 0; i < image.length; i++) {
            if (imageView.getTag() != null && (int) imageView.getTag() == image[i]) {
                currentIndex = i;
                break;
            }
        }

        if (v.getId() == R.id.button) {
            imageView.setImageResource(image[0]);
            imageView.setTag(image[0]);
            Toast.makeText(this, "第一张.....", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.button2) {
            int prevIndex = (currentIndex - 1 + image.length) % image.length;
            imageView.setImageResource(image[prevIndex]);
            imageView.setTag(image[prevIndex]);
        } else if (v.getId() == R.id.button3) {

            int nextIndex = (currentIndex + 1) % image.length;
            imageView.setImageResource(image[nextIndex]);
            imageView.setTag(image[nextIndex]);
        } else if (v.getId() == R.id.button4) {
            imageView.setImageResource(image[image.length - 1]);
            imageView.setTag(image[image.length - 1]);
            Toast.makeText(this, "最后一张.....", Toast.LENGTH_SHORT).show();
        }
    }
}
