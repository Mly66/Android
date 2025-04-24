package cn.nbmly.test0424;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyView extends View {
    int color;
    String text;
    float size;
    String content;
    Paint paint;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        color = ta.getColor(R.styleable.MyView_my_color, 0);
        text = ta.getString(R.styleable.MyView_my_text);
        size = ta.getDimension(R.styleable.MyView_my_size, 16);
        content = ta.getString(R.styleable.MyView_my_content);

        ta.recycle();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(size);
        paint.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getSize(widthMeasureSpec);
        int height = getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int getSize(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = size; // 精确尺寸
                Log.i("MyView", "EXACTLY");
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(200, size); // 自定义最大大小
                Log.i("MyView", "AT_MOST");
                break;
            case MeasureSpec.UNSPECIFIED:
                result = 200; // 默认大小
                Log.i("MyView", "UNSPECIFIED");
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(color);

        if (text != null) {
            canvas.drawText(text, 100, 100, paint);
        }
        if (content != null) {
            canvas.drawText(content, 100, 200, paint);
        }
    }
}
