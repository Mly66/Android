package cn.nbmly.shop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import cn.nbmly.shop.model.User;

public class SharedPreferencesUtil {
    private static final String PREF_NAME = "shop_pref";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER = "user";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void saveToken(Context context, String token) {
        getPrefs(context).edit().putString(KEY_TOKEN, token).apply();
    }

    public static String getToken(Context context) {
        return getPrefs(context).getString(KEY_TOKEN, "");
    }

    public static void saveUser(Context context, User user) {
        String userJson = new Gson().toJson(user);
        getPrefs(context).edit().putString(KEY_USER, userJson).apply();
    }

    public static User getUser(Context context) {
        String userJson = getPrefs(context).getString(KEY_USER, "");
        if (userJson.isEmpty()) {
            return null;
        }
        return new Gson().fromJson(userJson, User.class);
    }

    public static void clear(Context context) {
        getPrefs(context).edit().clear().apply();
    }
}