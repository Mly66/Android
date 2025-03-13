package cn.nbmly.test0310;

import android.util.Log;

public class Second {
    // 静态方法，可以直接被 Activity 调用
    public static void callMethod() {
        Log.i("MainActivity", "onCreate - Activity 创建");
    }
}