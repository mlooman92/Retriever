package com.matthewlooman.retriever.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.matthewlooman.retriever.R;
import com.matthewlooman.retriever.RetrieverApp;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject SharedPreferences mEncryptedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ((RetrieverApp)getApplication()).getComponent().inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    String registrationKey = null;   // inserted here for testing purposes  TODO Fix this code
//    String registrationKey = mEncryptedPreferences.getString(
//            this.getResources().getString(R.string.registration_key_pref_key)
//            , null
//    );
    if(registrationKey==null){
      if(registrationKey == null) {
        // Open the Registration activity
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);

      }
      else {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("PLACEHOLDER")
                .setMessage("This should be moving to the Home Activity")
                .setPositiveButton(R.string.ok_button_label, (dialog, id) -> {
                  // Just close the dialog
                });
        builder.create().show();
//        // Open the Home Screen activity
//        Intent intent = new Intent(this,HomeActivity.class);
//        startActivity(intent);
      }

      // Close this screen.

    }
  }
}
