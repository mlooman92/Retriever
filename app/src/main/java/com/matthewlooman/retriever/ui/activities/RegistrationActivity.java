package com.matthewlooman.retriever.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.matthewlooman.retriever.R;
import com.matthewlooman.retriever.RetrieverApp;
import com.matthewlooman.retriever.databinding.ActivityRegistrationBinding;
import com.matthewlooman.retriever.repository.Repository;
import com.matthewlooman.retriever.rest.exception.DuplicateDeviceNameException;
import com.matthewlooman.retriever.rest.exception.ResponseException;
import com.matthewlooman.retriever.rest.network.ItemTypeService;
import com.matthewlooman.retriever.rest.network.RegistrationService;
import com.matthewlooman.retriever.rest.network.RetrofitClientInstance;
import com.matthewlooman.retriever.rest.resource.Item;
import com.matthewlooman.retriever.rest.resource.ItemTypeResource;
import com.matthewlooman.retriever.model.RegistrationData;
import com.matthewlooman.retriever.rest.resource.ServerRegistration;
import com.matthewlooman.retriever.ui.view.RegistrationContract;
import com.matthewlooman.retriever.ui.view.RegistrationViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View {

  private final String TAG = this.getClass().getSimpleName();
  TextView textViewProgressUpdate;
  ProgressBar progressBar;
  CheckBox checkBoxDuplicateRegistration;
  RegistrationData mRegistrationData;
  @Inject SharedPreferences mEncryptedPreferences;
  @Inject Repository mRepository;
  RegistrationViewModel mRegistrationViewModel;
  Disposable mDisposable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ((RetrieverApp)getApplication()).getComponent().inject(this);
    super.onCreate(savedInstanceState);
    mRegistrationData = new RegistrationData(this,mEncryptedPreferences);
    ActivityRegistrationBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_registration);
    mRegistrationViewModel = new RegistrationViewModel(this);
    binding.setRegistrationData(mRegistrationData);
    binding.setRegistrationViewModel(mRegistrationViewModel);
    textViewProgressUpdate = findViewById(R.id.textViewProgressUpdate);
    progressBar = findViewById(R.id.progressBar);
    checkBoxDuplicateRegistration = findViewById(R.id.checkBoxDuplicateRegistration);
  }

  @Override
  public void registerClient(RegistrationData registrationData){
    String message = "uri: " + registrationData.getUri() + "; "
            + "username: " + registrationData.getUsername() + "; "
            + "deviceName: " + registrationData.getDeviceName() + "; ";
    Log.d(TAG,message);
//    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
//    builder.setTitle("Register Client")
//            .setMessage(message)
//            .setPositiveButton(R.string.ok_button_label, (dialog, id) -> {
//              // Just close the dialog
//            });
//    builder.create().show();

    textViewProgressUpdate.setVisibility(View.VISIBLE);
    textViewProgressUpdate.setText(R.string.registration_in_progress);
    progressBar.setVisibility(View.VISIBLE);

    RegistrationService retrofit = RetrofitClientInstance.getRetrofitInstance(registrationData.getUri()
            ,registrationData.getUsername()
            ,registrationData.getPassword()
    )
            .create(RegistrationService.class);

    Call<ServerRegistration> call = retrofit.registerDevice(registrationData.getDeviceName()
            , checkBoxDuplicateRegistration.isChecked() ? "Y" : "N"
    );

    call.enqueue(new Callback<ServerRegistration>() {
      @Override
      public void onResponse(Call<ServerRegistration> call, Response<ServerRegistration> response) {
        // Extract the registration identifier
        if(response.isSuccessful()){
          Log.d(TAG,"SUCCESS!");
          ServerRegistration serverRegistration = response.body();
          mRegistrationData.setRegistrationKey(serverRegistration.getDeviceIdentifier());
          AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
          builder.setTitle(R.string.registration_success_title)
                  .setMessage(getApplication().getResources().getString(R.string.registration_key_label)
                          + " " + mRegistrationData.getRegistrationKey())
                  .setPositiveButton(R.string.ok_button_label, (dialog, id) -> {
                    // Just close the dialog
                  });
          builder.create().show();

          // TODO Build/Rebuild the data import
          textViewProgressUpdate.setText(R.string.loading_data_message);
          loadServerData();

          progressBar.setVisibility(View.GONE);
          textViewProgressUpdate.setVisibility(View.GONE);
//        // TODO Open the Home Screen activity
//        Intent intent = new Intent(this,HomeActivity.class);
//        startActivity(intent);
        }
        else {
          switch(response.code() ){
            case 401:  // Authorization Failure
              Log.e(TAG,response.toString());
              progressBar.setVisibility(View.GONE);
              textViewProgressUpdate.setVisibility(View.GONE);
              AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
              builder.setTitle(R.string.registration_failure_title)
                      .setMessage(R.string.authorization_failure_message)
                      .setPositiveButton(R.string.ok_button_label, (dialog, id) -> {
                        // Just close the dialog
                      });
              builder.create().show();

              mRegistrationData.setRegistrationKey(null);
              break;
            case 499:  // Duplicate Device Name failure
              Log.e(TAG,response.errorBody().toString());
              progressBar.setVisibility(View.GONE);
              textViewProgressUpdate.setVisibility(View.GONE);
              AlertDialog.Builder builder3 = new AlertDialog.Builder(RegistrationActivity.this);
              builder3.setTitle(R.string.registration_failure_title)
                      .setMessage(R.string.duplicate_device_registration_warning)
                      .setPositiveButton(R.string.ok_button_label, (dialog, id) -> {
                        // Just close the dialog
                      });
              builder3.create().show();
              DuplicateDeviceNameException g = new DuplicateDeviceNameException(getApplication().getResources()
                      .getString(R.string.duplicate_device_registration_warning));
              mRegistrationData.setRegistrationKey(null);
              break;
            default:
              Log.e(TAG+"ResponseError",response.toString());
              Log.e(TAG,response.errorBody().toString());
              progressBar.setVisibility(View.GONE);
              textViewProgressUpdate.setVisibility(View.GONE);
              AlertDialog.Builder builder2 = new AlertDialog.Builder(RegistrationActivity.this);
              builder2.setTitle(R.string.registration_failure_title)
                      .setMessage(response.errorBody().toString())
                      .setPositiveButton(R.string.ok_button_label, (dialog, id) -> {
                        // Just close the dialog
                      });
              builder2.create().show();
              ResponseException re = new ResponseException(response.errorBody().toString());
              mRegistrationData.setRegistrationKey(null);
              break;
          }
        }
      }

      @Override
      public void onFailure(Call<ServerRegistration> call, Throwable t) {
        t.printStackTrace();
        Log.e(TAG,call.toString() + " // t: " + t.getLocalizedMessage());
        progressBar.setVisibility(View.GONE);
        textViewProgressUpdate.setVisibility(View.GONE);

        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
        builder.setTitle(R.string.registration_failure_title)
                .setMessage(t.getLocalizedMessage())
                .setPositiveButton(R.string.ok_button_label, (dialog, id) -> {
                  // Just close the dialog
                });
        builder.create().show();
        mRegistrationData.setRegistrationKey(null);
      }
    });
  }

  private void loadServerData() {
    Repository repository = new Repository(mRegistrationData);
    Retrofit retrofit = repository.getRetrofit(repository.getOkHttpClient());
    ItemTypeService service = retrofit.create(ItemTypeService.class);
    Single<Item<ItemTypeResource>> call = service.loadAll();
    call.subscribeOn(Schedulers.io());
    call.observeOn(AndroidSchedulers.mainThread());// optional if you do not wish to override the default behavior
    call.subscribeWith(new SingleObserver<Item<ItemTypeResource>>() {

      @Override
      public void onSubscribe(@NonNull Disposable d) {

      }

      @Override
      public void onSuccess(@NonNull Item<ItemTypeResource> item) {
        View view = getWindow().getDecorView().getRootView();
        String label = getApplication().getResources().getString(R.string.item_type_label);
        for (int i = 0; i < item.getItems().size(); i++) {
          String message = label + " " + item.getItems().get(i).getItemTypeName();
          Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
          snackbar.show();
        }
        // TODO Add code to store ./rest/resource/ItemTypeResource as ./model/ItemType
      }

      @Override
      public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
        builder.setTitle(R.string.load_item_type_title)
                .setMessage(e.getLocalizedMessage())
                .setPositiveButton(R.string.ok_button_label, (dialog, id) -> {
                  // Just close the dialog
                });
        builder.create().show();
      }

    });
  }

  @Override
  public void showPassword(CheckBox checkBox, EditText editTextPassword){

    if(checkBox.isChecked()){
      editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    } else {
      editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
  }

  @Override
  public void clientRegistered() {

  }

  protected void onDestroy() {
    this.mDisposable.dispose();
    super.onDestroy();
  }}
