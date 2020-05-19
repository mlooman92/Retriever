package com.matthewlooman.retriever.rest.network;

import android.util.Log;

import java.io.IOException;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
  private static final String TAG = "RetrofitClientInstance";
  private static Retrofit retrofit;

  public static Retrofit getRetrofitInstance(String baseUrl, String username, String password) {
    if (retrofit == null) {
      OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
      final String authToken = Credentials.basic(username, password);

      Log.d(TAG,"AuthToken: " + authToken);
      //Create a new Interceptor.
      Interceptor headerAuthorizationInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
          okhttp3.Request request = chain.request();
          Headers headers = request.headers().newBuilder().add("Authorization", authToken).build();
          request = request.newBuilder().headers(headers).build();
          return chain.proceed(request);
        }
      };

      //Add the interceptor to the client builder.
      clientBuilder.addInterceptor(headerAuthorizationInterceptor);

      retrofit = new retrofit2.Retrofit.Builder()
              .baseUrl(baseUrl)
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
              .client(clientBuilder.build())
              .build();
    }
    return retrofit;
  }
}
