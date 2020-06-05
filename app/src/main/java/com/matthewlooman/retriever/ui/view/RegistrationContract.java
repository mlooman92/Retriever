package com.matthewlooman.retriever.ui.view;

import android.widget.CheckBox;
import android.widget.EditText;

import com.matthewlooman.retriever.model.RegistrationData;

public class RegistrationContract {
  public interface Presenter{
    void onRegisterClient(RegistrationData registrationData);
    void onShowPassword(CheckBox checkBox , EditText editTextPassword);
  }

  public interface View{
    void registerClient(RegistrationData registrationData );
    void showPassword(CheckBox checkBox , EditText editTextPassword);
  }

}
