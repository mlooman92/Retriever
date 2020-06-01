package com.matthewlooman.retriever.di.component;

import com.matthewlooman.retriever.di.modules.AppModule;
import com.matthewlooman.retriever.di.modules.RepoModule;
import com.matthewlooman.retriever.di.modules.PrefModule;
import com.matthewlooman.retriever.ui.activities.MainActivity;
import com.matthewlooman.retriever.ui.activities.RegistrationActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class,PrefModule.class, RepoModule.class})
public interface AppComponent {
  void inject(MainActivity activity);
  void inject(RegistrationActivity registrationActivity);
  void inject(PrefModule prefModule);
}
