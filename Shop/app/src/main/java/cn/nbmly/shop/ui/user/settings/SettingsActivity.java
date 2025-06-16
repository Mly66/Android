package cn.nbmly.shop.ui.user.settings;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import cn.nbmly.shop.databinding.ActivitySettingsBinding;
import cn.nbmly.shop.ui.user.about.AboutActivity;
import cn.nbmly.shop.ui.user.privacy.PrivacyActivity;
import cn.nbmly.shop.ui.user.notification.NotificationActivity;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    private SettingsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupViewModel();
        setupClickListeners();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
    }

    private void setupClickListeners() {
        binding.notificationSetting.setOnClickListener(v -> {
            startActivity(new Intent(this, NotificationActivity.class));
        });

        binding.privacySetting.setOnClickListener(v -> {
            startActivity(new Intent(this, PrivacyActivity.class));
        });

        binding.aboutUs.setOnClickListener(v -> {
            startActivity(new Intent(this, AboutActivity.class));
        });

        binding.clearCache.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("清除缓存")
                    .setMessage("确定要清除缓存吗？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        viewModel.clearCache();
                        Toast.makeText(this, "缓存已清除", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("取消", null)
                    .show();
        });

        binding.checkUpdate.setOnClickListener(v -> {
            viewModel.checkUpdate();
        });

        binding.logoutButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("退出登录")
                    .setMessage("确定要退出登录吗？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        viewModel.logout();
                        finish();
                    })
                    .setNegativeButton("取消", null)
                    .show();
        });
    }
}