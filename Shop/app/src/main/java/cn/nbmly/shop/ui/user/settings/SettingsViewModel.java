package cn.nbmly.shop.ui.user.settings;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import cn.nbmly.shop.util.PreferenceManager;

public class SettingsViewModel extends AndroidViewModel {

    private final PreferenceManager preferenceManager;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        preferenceManager = new PreferenceManager(application);
    }

    public void clearCache() {
        // TODO: 实现清除缓存逻辑
    }

    public void checkUpdate() {
        // TODO: 实现检查更新逻辑
    }

    public void logout() {
        preferenceManager.clearToken();
    }
}