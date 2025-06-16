package cn.nbmly.shop.ui.user;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.nbmly.shop.data.model.User;
import cn.nbmly.shop.data.repository.UserRepository;
import cn.nbmly.shop.util.PreferenceManager;

public class UserCenterViewModel extends AndroidViewModel {

    private final UserRepository userRepository;
    private final PreferenceManager preferenceManager;
    private final MutableLiveData<User> userInfo = new MutableLiveData<>();

    public UserCenterViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        preferenceManager = new PreferenceManager(application);
        loadUserInfo();
    }

    public LiveData<User> getUserInfo() {
        return userInfo;
    }

    private void loadUserInfo() {
        String token = preferenceManager.getToken();
        if (token != null) {
            userRepository.getUserInfo(token).observeForever(user -> {
                userInfo.setValue(user);
            });
        }
    }

    public void logout() {
        preferenceManager.clearToken();
        userInfo.setValue(null);
    }
}