package com.matthewlooman.retriever.di.modules;

import android.app.Application;
import android.content.SharedPreferences;

import com.matthewlooman.retriever.model.RegistrationData;
import com.matthewlooman.retriever.repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {
  private final String TAG = this.getClass().getSimpleName();

  @Provides
  @Singleton
  Repository getRepository(Application context, SharedPreferences encryptedPreferences) {
    return new Repository(context, new RegistrationData(context,encryptedPreferences));
  }

//  @Provides
//  @Singleton
//  public RetrieverDatabase getRetrieverDatabase(Application context){
//
//    Log.d(TAG,"Building RetrieverDatabase");
//    return Room.databaseBuilder(context, RetrieverDatabase.class, "RetrieverDatabase.db")
//            .build();
//  }
}
