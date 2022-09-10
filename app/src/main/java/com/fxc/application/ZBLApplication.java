package com.fxc.application;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import zuo.biao.library.base.BaseApplication;

public class ZBLApplication extends BaseApplication {
    private static final String TAG = "Application";

    private static ZBLApplication context;
    public static ZBLApplication getInstance() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

//    /**获取当前用户id
//     * @return
//     */
//    public long getCurrentUserId() {
//        currentUser = getCurrentUser();
//        Log.d(TAG, "getCurrentUserId  currentUserId = " + (currentUser == null ? "null" : currentUser.getId()));
//        return currentUser == null ? 0 : currentUser.getId();
//    }
//    /**获取当前用户phone
//     * @return
//     */
//    public String getCurrentUserPhone() {
//        currentUser = getCurrentUser();
//        return currentUser == null ? null : currentUser.getPhone();
//    }
//
//
//    private static User currentUser = null;
//    public User getCurrentUser() {
//        if (currentUser == null) {
//            currentUser = DataManager.getInstance().getCurrentUser();
//        }
//        return currentUser;
//    }
//
//    public void saveCurrentUser(User user) {
//        if (user == null) {
//            Log.e(TAG, "saveCurrentUser  currentUser == null >> return;");
//            return;
//        }
//        if (user.getId() <= 0 && StringUtil.isNotEmpty(user.getName(), true) == false) {
//            Log.e(TAG, "saveCurrentUser  user.getId() <= 0" +
//                    " && StringUtil.isNotEmpty(user.getName(), true) == false >> return;");
//            return;
//        }
//
//        currentUser = user;
//        DataManager.getInstance().saveCurrentUser(currentUser);
//    }
//
//    public void logout() {
//        currentUser = null;
//        DataManager.getInstance().saveCurrentUser(currentUser);
//    }
//
//    /**判断是否为当前用户
//     * @param userId
//     * @return
//     */
//    public boolean isCurrentUser(long userId) {
//        return DataManager.getInstance().isCurrentUser(userId);
//    }
//
//    public boolean isLoggedIn() {
//        return getCurrentUserId() > 0;
//    }


}
