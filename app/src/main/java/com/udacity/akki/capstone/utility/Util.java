package com.udacity.akki.capstone.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.GetTokenResult;

/**
 * Created by 836158 on 09-02-2017.
 */

public class Util {

    private static SharedPreferences.Editor getSharedPreferenceEditor(Context mContext) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        return prefs.edit();
    }

    private static SharedPreferences getSharedPreferenceInstance(Context mContext) {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public static void setUsername(Context context, String userName) {
        SharedPreferences.Editor editor = getSharedPreferenceEditor(context);
        editor.putString("UserName", userName).commit();
    }

    public static String getUserName(Context context) {
        SharedPreferences instance = getSharedPreferenceInstance(context);
        return instance.getString("UserName", "");
    }

    public static void setPassword(Context context, String password) {
        SharedPreferences.Editor editor = getSharedPreferenceEditor(context);
        editor.putString("Password", password).commit();
    }

    public static String getPassword(Context context) {
        SharedPreferences instance = getSharedPreferenceInstance(context);
        return instance.getString("Password", "");
    }

    public static void setUid(Context context, String uid) {
        SharedPreferences.Editor editor = getSharedPreferenceEditor(context);
        editor.putString("UID", uid).commit();
    }

    public static String getUid(Context context) {
        SharedPreferences instance = getSharedPreferenceInstance(context);
        return instance.getString("UID", "");
    }

    public static void setToken(Context context, String token) {
        SharedPreferences.Editor editor = getSharedPreferenceEditor(context);
        editor.putString("AuthToken", token).commit();
    }

    public static String getToken(Context context) {
        SharedPreferences instance = getSharedPreferenceInstance(context);
        return instance.getString("AuthToken", "");
    }


    public static Boolean isStringNullOrEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    public static void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}
