package com.matthewlooman.retriever.repository;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.matthewlooman.retriever.model.ItemType;
import com.matthewlooman.retriever.model.RegistrationData;
import com.matthewlooman.retriever.rest.network.ItemTypeService;
import com.matthewlooman.retriever.rest.resource.Item;
import com.matthewlooman.retriever.rest.resource.ItemTypeResource;
import com.matthewlooman.retriever.room.RetrieverDatabase;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
  private final String TAG = this.getClass().getSimpleName();
  private String mAuthToken = null;
  private String mBaseUrl = null;
  private Retrofit mRetrofit = null;
  private RetrieverDatabase mRetrieverDatabase;

  // region Constructors
  public Repository(Context context , String baseUrl, String authToken){
    mBaseUrl = baseUrl;
    mAuthToken = authToken;
    getRetrieverDatabase(context);
  }

  public Repository(Context context, String baseUrl, String userName, String password){
    this(context, baseUrl , Credentials.basic(userName,password));
  }

  public Repository(Context context , RegistrationData registrationData){
    this(context, registrationData.getUri()
            , Credentials.basic(registrationData.getUsername(),registrationData.getPassword())
            );
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
              .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
              .client(client)
              .build();
    }
    return mRetrofit;
  }

  public RetrieverDatabase getRetrieverDatabase(Context context) {
    if(mRetrieverDatabase==null){
      mRetrieverDatabase = Room.databaseBuilder(context, RetrieverDatabase.class, "RetrieverDatabase.db")
            .build();
    }
    return mRetrieverDatabase;
  }
  // region ItemType Exposed
  // Expose the ItemType Dao.
  public List<ItemType> getItemTypes(){
    return mRetrieverDatabase.itemTypeDao().getItemTypes();
  }
  public ItemType getItemType(String itemTypeName){
    return mRetrieverDatabase.itemTypeDao().getItemType(itemTypeName);
  }
  public void deleteItemType(ItemType itemType){
    mRetrieverDatabase.itemTypeDao().delete(itemType);
  }
  public void insertItemType(ItemType itemType){
    mRetrieverDatabase.itemTypeDao().save(itemType);
  }
  // endregion

  // region Data Load Routines
  public Observable<ItemType> downloadItemTypes(){

    Retrofit retrofit = getRetrofit(getOkHttpClient());
    ItemTypeService itemTypeService = retrofit.create(ItemTypeService.class);
    Observable<Item<ItemTypeResource>> call = itemTypeService.loadItemTypes(0);
    AtomicInteger offset = new AtomicInteger();
    offset.set(0);

    Observable<ItemType> output = Observable.just(0)
            .subscribeOn(Schedulers.io())
            .flatMap(itemList -> itemTypeService.loadItemTypes(offset.get()))
            .repeat()
            .takeUntil(item -> {
              Log.d(TAG,"incrementing offset = " + String.valueOf(item.getOffset() + item.getCount()) );
              offset.set(item.getOffset() + item.getCount());
              return !item.hasMore();
            })
            .flatMap( item -> Observable.fromIterable(item.getItems()))
            .map(itemTypeResource -> { ItemType itemType = new ItemType();
              itemType.setItemTypeName(itemTypeResource.getItemTypeName());
              itemType.setItemTypeAbbreviationCode(itemTypeResource.getItemTypeAbbreviationCode());
              itemType.setItemTypeDescription(itemTypeResource.getItemTypeDescription());
              itemType.setItemTypeSuperTypeName(itemTypeResource.getItemTypeSuperTypeName());
              insertItemType(itemType);
              return itemType;
            })
            .observeOn(AndroidSchedulers.mainThread());

    return output;
  }

  // endregion
}
