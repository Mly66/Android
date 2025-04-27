package cn.nbmly.test0427;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    private View colorBlock;
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private TextView redValueText, greenValueText, blueValueText;
    private int red = 0, green = 0, blue = 0;

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

        initViews();
        setupListeners();
        updateColorBlock();
    }

    private void initViews() {
        colorBlock = findViewById(R.id.colorBlock);

        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);

        redValueText = findViewById(R.id.redValueText);
        greenValueText = findViewById(R.id.greenValueText);
        blueValueText = findViewById(R.id.blueValueText);
    }

    private void setupListeners() {
        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getId() == R.id.redSeekBar) {
                    red = progress;
                    redValueText.setText(String.valueOf(progress));
                } else if (seekBar.getId() == R.id.greenSeekBar) {
                    green = progress;
                    greenValueText.setText(String.valueOf(progress));
                } else if (seekBar.getId() == R.id.blueSeekBar) {
                    blue = progress;
                    blueValueText.setText(String.valueOf(progress));
                }

                updateColorBlock();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    private void updateColorBlock() {
        int color = Color.rgb(red, green, blue);
        colorBlock.setBackgroundColor(color);
    }
}