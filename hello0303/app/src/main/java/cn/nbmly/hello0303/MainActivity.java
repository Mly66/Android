package cn.nbmly.hello0303;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "onCreate called");
        Log.v("MainActivity", "onCreate called");
        Log.w("MainActivity", "onCreate called");
        Log.d("MainActivity", "onCreate called");
        Log.e("MainActivity", "onCreate called");
        Log.wtf("MainActivity", "onCreate called");
        System.out.println("hello world");

        ListView listView = findViewById(R.id.app_list);
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> appList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        AppListAdapter adapter = new AppListAdapter(this, appList, packageManager);
        listView.setAdapter(adapter);

        Button uninstallButton = findViewById(R.id.uninstall_button);
        uninstallButton.setOnClickListener(v -> {
            List<ApplicationInfo> selectedApps = adapter.getSelectedApps();
            if (selectedApps.isEmpty()) {
                Toast.makeText(MainActivity.this, "No apps selected", Toast.LENGTH_SHORT).show();
                return;
            }

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("确认卸载")
                    .setMessage("您确定要卸载所选的应用吗？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        for (ApplicationInfo appInfo : selectedApps) {
                            Intent intent = new Intent(Intent.ACTION_DELETE);
                            intent.setData(Uri.parse("package:" + appInfo.packageName));
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        });
    }
}