package com.matthewlooman.retriever.di.modules;

import com.matthewlooman.retriever.repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {

  @Provides
  @Singleton
  public Repository getRepository() {
    return new Repository();
  }
}
