package cn.nbmly.test0605;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class MyReceiver extends BroadcastReceiver {

    private final TextView tvMessage;

    public MyReceiver(TextView tvMessage) {
        this.tvMessage = tvMessage;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("cn.nbmly.MY_BROADCAST".equals(intent.getAction())) {
            String msg = intent.getStringExtra("msg");
            tvMessage.setText("收到广播内容: " + msg);
        }
    }

}
