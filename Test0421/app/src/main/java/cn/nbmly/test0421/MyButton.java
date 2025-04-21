package cn.nbmly.test0421;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class MyButton extends androidx.appcompat.widget.AppCompatButton {

    public MyButton(Context context) {
        super(context);
        init(null);
    }

    public MyButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        setText("NBMLY");
        setTextSize(20);
        setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black));
    }
}
