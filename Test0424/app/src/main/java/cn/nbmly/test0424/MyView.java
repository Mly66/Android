package cn.nbmly.test0424;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyView extends View {
    private int color = Color.WHITE; // 默认黑色
    private String text;
    private float size = 16f; // 默认文字大小
    private String content;
    private Paint paint;


    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        color = ta.getColor(R.styleable.MyView_my_color, color);
        text = ta.getString(R.styleable.MyView_my_text);
        size = ta.getDimension(R.styleable.MyView_my_size, size);
        content = ta.getString(R.styleable.MyView_my_content);
        ta.recycle();

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(size);
        paint.setColor(color);
    }

    // 设置颜色
    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    // 设置文本
    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    // 设置文字大小
    public void setTextSize(float size) {
        this.size = size;
        paint.setTextSize(size);
        invalidate();
    }

    // 设置内容
    public void setContent(String content) {
        this.content = content;
        invalidate();
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
//        canvas.drawColor(color);
//
//        paint.setColor(0xFFFFFFFF); // 设置文字颜色为白色，以便在背景上可见
//
//        if (text != null) {
//            canvas.drawText(text, 100, 100, paint);
//        }
//        if (content != null) {
//            canvas.drawText(content, 100, 200, paint);
//        }
        int r = getWidth() / 2;
        int c = getHeight() / 2;
        paint.setColor(Color.GREEN);
        canvas.drawCircle(r, c, r, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(getContext(), "1231", Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }
}
