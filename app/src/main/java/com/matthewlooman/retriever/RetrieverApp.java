package com.matthewlooman.retriever;

import android.app.Application;

import com.matthewlooman.retriever.di.component.AppComponent;
import com.matthewlooman.retriever.di.component.DaggerAppComponent;
import com.matthewlooman.retriever.di.modules.AppModule;
import com.matthewlooman.retriever.di.modules.PrefModule;
import com.matthewlooman.retriever.di.modules.RepoModule;


public class RetrieverApp extends Application {
  private AppComponent mAppComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    mAppComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .repoModule(new RepoModule())
            .prefModule(new PrefModule())
            .build();
  }

  public AppComponent getComponent() {
    return mAppComponent;
  }
}