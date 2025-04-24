//package cn.nbmly.test0424;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//import android.view.View;
//
//import androidx.annotation.Nullable;
//
//public class MyView1 extends View {
//
//    private static final String DEFAULT_TEXT = "默认文本";
//    private static final int DEFAULT_COLOR = Color.BLACK;
//    private static final float DEFAULT_SIZE = 50f;
//    private static final String DEFAULT_CONTENT = "";
//
//    private String myText;
//    private int myColor;
//    private float mySize;
//    private String myContent;
//
//    private Paint mTextPaint;
//    private Rect mTextBounds;
//
//    public MyView1(Context context) {
//        this(context, null);
//    }
//
//    public MyView1(Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initAttributes(context, attrs, defStyleAttr);
//        initPaint();
//    }
//
//    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        initAttributes(context, attrs, defStyleAttr);
//        initPaint();
//    }
//
//    private void initAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
//        myText = DEFAULT_TEXT;
//        myColor = DEFAULT_COLOR;
//        mySize = DEFAULT_SIZE;
//        myContent = DEFAULT_CONTENT;
//
//        if (attrs != null) {
//            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView, defStyleAttr, 0);
//
//            myText = ta.getString(R.styleable.MyView_my_text);
//            if (TextUtils.isEmpty(myText)) {
//                myText = DEFAULT_TEXT;
//            }
//
//            myColor = ta.getColor(R.styleable.MyView_my_color, DEFAULT_COLOR);
//            mySize = ta.getDimension(R.styleable.MyView_my_size, DEFAULT_SIZE);
//            myContent = ta.getString(R.styleable.MyView_my_content);
//
//            ta.recycle();
//        }
//    }
//
//    private void initPaint() {
//        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mTextPaint.setColor(myColor);
//        mTextPaint.setTextSize(mySize);
//        mTextPaint.setTextAlign(Paint.Align.CENTER);
//
//        mTextBounds = new Rect();
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        int width = getWidth();
//        int height = getHeight();
//        mTextPaint.getTextBounds(myText, 0, myText.length(), mTextBounds);
//
//        float x = width / 2f;
//        float y = height / 2f + mTextBounds.height() / 2f;
//        canvas.drawText(myText, x, y, mTextPaint);
//
//        if (!TextUtils.isEmpty(myContent)) {
//            float contentY = y + mTextBounds.height() + 20;
//            mTextPaint.setTextSize(mySize * 0.7f);
//            canvas.drawText(myContent, x, contentY, mTextPaint);
//        }
//    }
//
//    public void setMyText(String text) {
//        this.myText = TextUtils.isEmpty(text) ? DEFAULT_TEXT : text;
//        invalidate();
//    }
//
//    public void setMyColor(int color) {
//        this.myColor = color;
//        if (mTextPaint != null) {
//            mTextPaint.setColor(color);
//        }
//        invalidate();
//    }
//
//    public void setMySize(float size) {
//        this.mySize = size;
//        if (mTextPaint != null) {
//            mTextPaint.setTextSize(size);
//        }
//        invalidate();
//    }
//
//    public void setMyContent(String content) {
//        this.myContent = content;
//        invalidate();
//    }
//}
