package com.matthewlooman.retriever.repository;

import com.matthewlooman.retriever.model.RegistrationData;

import java.io.IOException;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
  private String mAuthToken = null;
  private String mBaseUrl = null;
  private Retrofit mRetrofit = null;

  // region Constructors
  public Repository(){}

  public Repository(String baseUrl, String authToken){
    mBaseUrl = baseUrl;
    mAuthToken = authToken;
  }

  public Repository(String baseUrl, String userName, String password){
    mBaseUrl = baseUrl;
    mAuthToken = Credentials.basic(userName,password);
  }

  public Repository(RegistrationData registrationData){
    mBaseUrl = registrationData.getUri();
    mAuthToken = Credentials.basic(registrationData.getUsername(),registrationData.getPassword());
  }
  // endregion Constructors

  // region getters / setters
  public String getBaseUrl() {    return mBaseUrl;  }

  public void setBaseUrl(String baseUrl){
    mBaseUrl = baseUrl;
    mRetrofit = null;
  }

  public String getAuthToken(){    return mAuthToken;  }

  public void setAuthToken(String authToken) {
    mAuthToken = authToken;
    mRetrofit = null;
  }

  public void setAuthToken(String username, String password) { setAuthToken(Credentials.basic(username,password));}
  // endregion

  public OkHttpClient getOkHttpClient(){
    // TODO Add error handling code for the case when mAuthToken is null.
    //Create a new Interceptor.
    Interceptor headerAuthorizationInterceptor = new Interceptor() {
      @Override
      public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Request request = chain.request();
        Headers headers = request.headers().newBuilder().add("Authorization", mAuthToken).build();
        request = request.newBuilder().headers(headers).build();
        return chain.proceed(request);
      }
    };

    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    //Add the interceptor to the client builder.
    clientBuilder.addInterceptor(headerAuthorizationInterceptor);
    return clientBuilder.build();
  }

  public Retrofit getRetrofit(OkHttpClient client){
    // TODO Add error handling for the case when mAuthToken or mBaseUrl is null
    if(mRetrofit==null) {
      mRetrofit = new retrofit2.Retrofit.Builder()
              .baseUrl(mBaseUrl)
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
              .client(client)
              .build();
    }
    return mRetrofit;
  }

}
