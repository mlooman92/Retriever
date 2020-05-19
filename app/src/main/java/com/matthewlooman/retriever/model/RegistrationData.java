package com.matthewlooman.retriever.model;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.matthewlooman.retriever.R;

public class RegistrationData extends BaseObservable {
  private Context mContext;
  private SharedPreferences mEncryptedPreferences;

  public RegistrationData(Context context, SharedPreferences encryptedPreferences){
    mContext = context;
    mEncryptedPreferences = encryptedPreferences;
  }

  @Bindable
  public String getUri() {
    return mEncryptedPreferences.getString(mContext.getResources().getString(R.string.uri_pref_key),null);
  }

  public void setUri(String uri) {
    SharedPreferences.Editor editor = mEncryptedPreferences.edit();
    editor.putString(mContext.getResources().getString(R.string.uri_pref_key),uri);
    editor.apply();
  }

  @Bindable
  public String getUsername() {
    return mEncryptedPreferences.getString(mContext.getResources().getString(R.string.username_pref_key),null);
  }

  public void setUsername(String username) {
    SharedPreferences.Editor editor = mEncryptedPreferences.edit();
    editor.putString(mContext.getResources().getString(R.string.username_pref_key),username);
    editor.apply();
  }

  @Bindable
  public String getPassword() {
    return mEncryptedPreferences.getString(mContext.getResources().getString(R.string.password_pref_key),null);
  }

  public void setPassword(String password) {
    SharedPreferences.Editor editor = mEncryptedPreferences.edit();
    editor.putString(mContext.getResources().getString(R.string.password_pref_key),password);
    editor.apply();
  }

  @Bindable
  public String getDeviceName() {
    return mEncryptedPreferences.getString(mContext.getResources().getString(R.string.device_name_pref_key),android.os.Build.MODEL);
  }

  public void setDeviceName(){
    setDeviceName(android.os.Build.MODEL);
  }
  public void setDeviceName(String deviceName) {
    SharedPreferences.Editor editor = mEncryptedPreferences.edit();
    editor.putString(mContext.getResources().getString(R.string.device_name_pref_key),deviceName);
    editor.apply();
  }

  @Bindable
  public String getRegistrationKey() {
    return mEncryptedPreferences.getString(mContext.getResources().getString(R.string.registration_key_pref_key),null);
  }

  public void setRegistrationKey(String registrationKey) {
    SharedPreferences.Editor editor = mEncryptedPreferences.edit();
    editor.putString(mContext.getResources().getString(R.string.registration_key_pref_key),registrationKey);
    editor.apply();
  }

  @Override
  public String toString(){
    return "Key: " + getRegistrationKey() + "; uri: " + getUri() + "; username: " + getUsername()
            + "; deviceName: " + getDeviceName();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(@Nullable Object obj) {
    if((obj != null) && (obj.toString() == this.toString())){
      return true;
    } else {
      return false;
    }
  }
}
