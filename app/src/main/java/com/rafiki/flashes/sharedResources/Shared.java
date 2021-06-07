package com.rafiki.flashes.sharedResources;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.rafiki.flashes.model.User;

public class Shared {
    private Context context;
    private static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private static final String SHARED_PREFERENCES_ID = "SHARED_PREFERENCES_ID";
    private static final String SHARED_PREFERENCES_EMAIL = "SHARED_PREFERENCES_EMAIL";
    private static final String SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME";
    private static final String SHARED_PREFERENCES_TOKEN = "SHARED_PREFERENCES_TOKEN";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    @SuppressLint("StaticFieldLeak")
    private static Shared instance;

    @SuppressLint("CommitPrefEdits")
    private Shared(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized Shared getInstance(Context context){
        if(instance == null){
            instance = new Shared(context);
        }
        return instance;
    }

    public void saveUser(User user){
        editor.putInt(SHARED_PREFERENCES_ID, user.getId());
        editor.putString(SHARED_PREFERENCES_NAME, user.getUsername());
        editor.putString(SHARED_PREFERENCES_EMAIL, user.getEmail());
        editor.putString(SHARED_PREFERENCES_TOKEN, user.getAccessToken());
        editor.apply();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getInt(SHARED_PREFERENCES_ID, -1) != -1;
    }

    public User getUser(){
        return new User(
                sharedPreferences.getInt(SHARED_PREFERENCES_ID, -1),
                sharedPreferences.getString(SHARED_PREFERENCES_NAME, null),
                sharedPreferences.getString(SHARED_PREFERENCES_EMAIL, null),
                sharedPreferences.getString(SHARED_PREFERENCES_TOKEN, null)
        );
    }

    public void logOut(){
        editor.clear();
        editor.apply();
    }
}
