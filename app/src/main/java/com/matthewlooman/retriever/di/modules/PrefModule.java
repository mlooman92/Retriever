package com.matthewlooman.retriever.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefModule {
  private final String TAG = this.getClass().getSimpleName();
  private static final String PREFERENCE_FILE_NAME = "retriever_encrypted_preferences";

  public PrefModule(){}

  @Provides
  @Singleton
  SharedPreferences provideEncryptedSharedPreferences(Application application) {
    try {
      String masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
      SharedPreferences encryptedPreferences = EncryptedSharedPreferences.create(PREFERENCE_FILE_NAME
              , masterKeys
              , application
              , EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV
              , EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
      );
      return encryptedPreferences;
    } catch(GeneralSecurityException | IOException e){
      e.printStackTrace();
      Log.d(TAG,e.getLocalizedMessage());
      return null;
    }
  }
}
