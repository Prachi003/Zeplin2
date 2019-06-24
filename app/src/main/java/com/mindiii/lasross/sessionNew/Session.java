package com.mindiii.lasross.sessionNew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mindiii.lasross.loginregistration.LoginActivity;
import com.mindiii.lasross.session_api.prefs.newPrefs.PreferencesHelper;

public class Session implements PreferencesHelper {

    private static final String REMEMBER_PWD = "password";
    private static final String REMEMBER_NAME = "email";
    private Context context;
    // private Activity activity;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static SharedPreferences.Editor editorRem;

    private static final String IS_LOGGEDIN = "isLoggedIn";
    private static final String Filter_id = "filterId";
    public SharedPreferences myprefRemember;
    private static final String PREF_NAMELAN = "lan";


    public Session(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();

        myprefRemember = this.context.getSharedPreferences(PREF_NAMELAN, Context.MODE_PRIVATE);
        editorRem = myprefRemember.edit();
        editorRem.apply();

    }

    public void createSession(UserInfo userInfo) {
        editor.putString("userId", userInfo.userId);
        editor.putString("cookie", userInfo.cookie);
        editor.putString("first_name", userInfo.first_name);
        editor.putString("last_name", userInfo.last_name);
        editor.putString("email", userInfo.email);
        editor.putString("profile_image", userInfo.profile_image);
        editor.putString("social_id", userInfo.social_id);
        editor.putString("description", userInfo.description);
        editor.putBoolean(IS_LOGGEDIN, true);

        editor.commit();
    }

    public void session_for_Login(String email, String password) {
        editorRem.putString("email", email);
        editorRem.putString("password", password);
        editorRem.commit();
    }

    public String getFirst_name() {
        return sharedPreferences.getString("first_name", "");
    }

    public String getLast_Name() {
        return sharedPreferences.getString("last_name", "");
    }

    public String getDescription() {
        return sharedPreferences.getString("description", "");
    }

    public String getProfile_image() {
        return sharedPreferences.getString("profile_image", "");
    }

    public void setProfile_image(String img) {
        editor.putString("profile_image", img);
        editor.apply();

    }

    public String getUserId() {
        return sharedPreferences.getString("userId", "");
    }

    public String getCookie() {
        return sharedPreferences.getString("cookie", "");
    }

    public String getProfession() {
        return sharedPreferences.getString("profession", "");
    }

    public String getProfileImage() {
        return sharedPreferences.getString("profileImage", "");
    }

    public String getEmail() {
        return myprefRemember.getString("email", "");
    }

    public String getPassword() {
        return myprefRemember.getString("password", "");
    }

    public void logout() {
        editor.clear();
        editor.apply();

        Intent showLogin = new Intent(context, LoginActivity.class);
        showLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        showLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(showLogin);

        ((Activity) context).finish();
    }

    public String[] getStringPreferenceRem() {
        String arr[] = new String[2];
        String name = myprefRemember.getString(REMEMBER_NAME, "");
        String pwd = myprefRemember.getString(REMEMBER_PWD, "");

        arr[0] = name;
        arr[1] = pwd;
        return arr;
    }

    public void setStringPreferenceRem(String name, String pwd) {
        editorRem.putString(REMEMBER_NAME, name);
        editorRem.putString(REMEMBER_PWD, pwd);
        editorRem.apply();

    }

    public void setDescription(String description) {
        editorRem.putString("description", description);
        editorRem.apply();

    }

    public void setSelected(String filterValue) {
        editor.putString(Filter_id, filterValue);
        editor.apply();
        editor.commit();
    }

    public String getSelected() {
        return sharedPreferences.getString(Filter_id, "");
    }

    public String getAuthToken() {
        return sharedPreferences.getString("authToken", "");
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGEDIN, false);
    }
}
