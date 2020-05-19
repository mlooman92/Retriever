package com.matthewlooman.retriever.ui.view;

import android.widget.CheckBox;
import android.widget.EditText;

import com.matthewlooman.retriever.model.RegistrationData;

public class RegistrationViewModel implements RegistrationContract.Presenter {
  private RegistrationContract.View mView;

  public RegistrationViewModel(RegistrationContract.View view){
    mView = view;
  }

  @Override
  public void onRegisterClient(RegistrationData registrationData) {
    mView.registerClient(registrationData);
  }

  @Override
  public void onShowPassword(CheckBox checkBox, EditText editTextPassword) {
    mView.showPassword(checkBox, editTextPassword);
  }

  @Override
  public void onClientRegistered() {
    mView.clientRegistered();
  }
}
