package cn.nbmly.test0612;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CalculateService extends Service {
    private final IBinder binder = new CalculateBinder();

    public class CalculateBinder extends Binder {
        CalculateService getService() {
            return CalculateService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    // 加法运算方法
    public int add(int a, int b) {
        return a + b;
    }

    // 减法运算方法
    public int subtract(int a, int b) {
        return a - b;
    }

    // 乘法运算方法
    public int multiply(int a, int b) {
        return a * b;
    }

    // 除法运算方法
    public int divide(int a, int b) {
        return a / b;
    }
}