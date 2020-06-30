package com.matthewlooman.retriever.repository;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.matthewlooman.retriever.model.Category;
import com.matthewlooman.retriever.model.ItemType;
import com.matthewlooman.retriever.model.Labor;
import com.matthewlooman.retriever.model.Location;
import com.matthewlooman.retriever.model.Organization;
import com.matthewlooman.retriever.model.Party;
import com.matthewlooman.retriever.model.Person;
import com.matthewlooman.retriever.model.Priority;
import com.matthewlooman.retriever.model.RegistrationData;
import com.matthewlooman.retriever.model.Tag;
import com.matthewlooman.retriever.model.Transition;
import com.matthewlooman.retriever.rest.network.ItemTypeService;
import com.matthewlooman.retriever.rest.network.LaborService;
import com.matthewlooman.retriever.rest.network.LocationService;
import com.matthewlooman.retriever.rest.network.CategoryService;
import com.matthewlooman.retriever.rest.network.PartyService;
import com.matthewlooman.retriever.rest.network.TransitionService;
import com.matthewlooman.retriever.rest.resource.Item;
import com.matthewlooman.retriever.rest.resource.ItemTypeResource;
import com.matthewlooman.retriever.rest.resource.LaborResource;
import com.matthewlooman.retriever.rest.resource.LocationResource;
import com.matthewlooman.retriever.rest.resource.CategoryResource;
import com.matthewlooman.retriever.rest.resource.OrganizationResource;
import com.matthewlooman.retriever.rest.resource.PersonResource;
import com.matthewlooman.retriever.rest.resource.TransitionResource;
import com.matthewlooman.retriever.room.DataTypeConverters;
import com.matthewlooman.retriever.room.RetrieverDatabase;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
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
              .fallbackToDestructiveMigration()  // TODO REMOVE BEFORE PRODUCTION!!!!!!
              .build();
    }
    return mRetrieverDatabase;
  }

  // region Dao Methods
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

  public List<Labor> getLabors(){return mRetrieverDatabase.laborDao().getLabors();}
  public Labor getLabor(String laborName){return mRetrieverDatabase.laborDao().getLabor(laborName);}
  public Labor getLabor(UUID itemIdentifier){return mRetrieverDatabase.laborDao().getLabor(itemIdentifier);}
  public void deleteLabor(Labor labor){mRetrieverDatabase.laborDao().delete(labor);}
  public void insertLabor(Labor labor){mRetrieverDatabase.laborDao().save(labor);}

  public List<Location> getLocations(){return mRetrieverDatabase.locationDao().getLocations();}
  public Location getLocation(String locationName){return mRetrieverDatabase.locationDao().getLocation(locationName);}
  public Location getLocation(UUID itemIdentifier){return mRetrieverDatabase.locationDao().getLocation(itemIdentifier);}
  public void deleteLocation(Location location){mRetrieverDatabase.locationDao().delete(location);}
  public void insertLocation(Location location){mRetrieverDatabase.locationDao().save(location);}

  public List<Priority> getPriorities(){return mRetrieverDatabase.priorityDao().getPriorities();}
  public Priority getPriority(String priorityName){return mRetrieverDatabase.priorityDao().getPriority(priorityName);}
  public Priority getPriority(UUID itemIdentifier){return mRetrieverDatabase.priorityDao().getPriority(itemIdentifier);}
  public void deletePriority(Priority priority){mRetrieverDatabase.priorityDao().delete(priority);}
  public void insertPriority(Priority priority){mRetrieverDatabase.priorityDao().save(priority);}
  
  public List<Tag> getTags(){return mRetrieverDatabase.tagDao().getTags();}
  public Tag getTag(String tagName){return mRetrieverDatabase.tagDao().getTag(tagName);}
  public Tag getTag(UUID itemIdentifier){return mRetrieverDatabase.tagDao().getTag(itemIdentifier);}
  public void deleteTag(Tag tag){mRetrieverDatabase.tagDao().delete(tag);}
  public void insertTag(Tag tag){mRetrieverDatabase.tagDao().save(tag);}

  public List<Transition> getTransitions(){return mRetrieverDatabase.transitionDao().getTransitions();}
  public Transition getTransition(UUID itemIdentifier){ return mRetrieverDatabase.transitionDao().getTransition(itemIdentifier);}
  public void deleteTransition(Transition transition){ mRetrieverDatabase.transitionDao().delete(transition);}
  public void insertTransition(Transition transition){ mRetrieverDatabase.transitionDao().save(transition);}

  public List<Party> getParties(){ return mRetrieverDatabase.partyDao().getParties();}
  public List<Person> getPersons(){return mRetrieverDatabase.personDao().getPersons();}
  public List<Organization> getOrganizations(){return mRetrieverDatabase.organizationDao().getOrganizations();}
  public Party getParty(UUID itemIdentifier){ return mRetrieverDatabase.partyDao().getParty(itemIdentifier);}
  public Party getParty(String partyName){return mRetrieverDatabase.partyDao().getParty(partyName);}
  public void deleteParty(Party party){mRetrieverDatabase.partyDao().delete(party);}
  public void insertParty(Party party){mRetrieverDatabase.partyDao().save(party);}


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
              offset.set(item.getOffset() + item.getCount());
              return !item.hasMore();
            })
            .flatMap( item -> Observable.fromIterable(item.getItems()))
            .map(this::itemTypeResourceToDatabase)
            .observeOn(AndroidSchedulers.mainThread());

    return output;
  }

  public Observable<Location> downloadLocations(){
    Retrofit retrofit = getRetrofit(getOkHttpClient());
    LocationService locationService = retrofit.create(LocationService.class);
    AtomicInteger offset = new AtomicInteger();
    offset.set(0);

    Observable<Location> output = Observable.just(0)
            .subscribeOn(Schedulers.io())
            .flatMap(itemList -> locationService.loadLocations(offset.get()))
            .repeat()
            .takeUntil(item -> {
              offset.set(item.getOffset() + item.getCount());
              return !item.hasMore();
            })
            .flatMap(item -> Observable.fromIterable(item.getItems()))
            .map(this::locationResourceToDatabase)
            .observeOn(AndroidSchedulers.mainThread());

    return output;
  }

  public Observable<Labor> downloadLabors(){
    Retrofit retrofit = getRetrofit(getOkHttpClient());
    LaborService laborService = retrofit.create(LaborService.class);
    AtomicInteger offset = new AtomicInteger();
    offset.set(0);

    Observable<Labor> output = Observable.just(0)
            .subscribeOn(Schedulers.io())
            .flatMap(itemList -> laborService.loadLabors(offset.get()))
            .repeat()
            .takeUntil(item -> {
              offset.set(item.getOffset() + item.getCount());
              return !item.hasMore();
            })
            .flatMap(item -> Observable.fromIterable(item.getItems()))
            .map(this::laborResourceToDatabase)
            .observeOn(AndroidSchedulers.mainThread());

    return output;

  }

  public Observable<Transition> downloadTransition(){
    Retrofit retrofit = getRetrofit(getOkHttpClient());
    TransitionService transitionService = retrofit.create(TransitionService.class);
    AtomicInteger offset = new AtomicInteger();
    offset.set(0);

    Observable<Transition> output = Observable.just(0)
            .subscribeOn(Schedulers.io())
            .flatMap(itemList -> transitionService.loadTransitions(offset.get()))
            .repeat()
            .takeUntil(item -> {
              offset.set(item.getOffset() + item.getCount());
              return !item.hasMore();
            })
            .flatMap(item -> Observable.fromIterable(item.getItems()))
            .map(this::transitionResourceToDatabase)
            .observeOn(AndroidSchedulers.mainThread());

    return output;

  }

  public Observable<Category> downloadTag(){
    Retrofit retrofit = getRetrofit(getOkHttpClient());
    CategoryService categoryService = retrofit.create(CategoryService.class);
    AtomicInteger offset = new AtomicInteger();
    offset.set(0);

    @NonNull 
    Observable<Category> output = Observable.just(0)
            .subscribeOn(Schedulers.io())
            .flatMap(itemList -> categoryService.loadCategories("tag",offset.get()))
            .repeat()
            .takeUntil(item -> {
              offset.set(item.getOffset() + item.getCount());
              return !item.hasMore();
            })
            .flatMap(item -> Observable.fromIterable(item.getItems()))
            .map(this::categoryResourceToDatabase)
            .observeOn(AndroidSchedulers.mainThread());

    return output;

  }

  public Observable<Category> downloadPriority(){
    Retrofit retrofit = getRetrofit(getOkHttpClient());
    CategoryService categoryService = retrofit.create(CategoryService.class);
    AtomicInteger offset = new AtomicInteger();
    offset.set(0);

    @NonNull
    Observable<Category> output = Observable.just(0)
            .subscribeOn(Schedulers.io())
            .flatMap(itemList -> categoryService.loadCategories("priority",offset.get()))
            .repeat()
            .takeUntil(item -> {
              offset.set(item.getOffset() + item.getCount());
              return !item.hasMore();
            })
            .flatMap(item -> Observable.fromIterable(item.getItems()))
            .map(this::categoryResourceToDatabase)
            .observeOn(AndroidSchedulers.mainThread());

    return output;
  }

  public Observable<Person> downloadPerson(){
    Retrofit retrofit = getRetrofit(getOkHttpClient());
    PartyService partyService = retrofit.create(PartyService.class);
    AtomicInteger offset = new AtomicInteger();
    offset.set(0);

    @NonNull
    Observable<Person> output = Observable.just(0)
            .subscribeOn(Schedulers.io())
            .flatMap(itemList -> partyService.loadPersons(offset.get()))
            .repeat()
            .takeUntil(item-> {
              offset.set(item.getOffset() + item.getCount());
              return !item.hasMore();
            })
            .flatMap(item -> Observable.fromIterable(item.getItems()))
            .map(this::personResourceToDatabase)
            .observeOn(AndroidSchedulers.mainThread());
    return output;
  }

  public Observable<Organization> downloadOrganization(){
    Retrofit retrofit = getRetrofit(getOkHttpClient());
    PartyService partyService = retrofit.create(PartyService.class);
    AtomicInteger offset = new AtomicInteger();
    offset.set(0);

    @NonNull
    Observable<Organization> output = Observable.just(0)
            .subscribeOn(Schedulers.io())
            .flatMap(itemList -> partyService.loadOrganizations(offset.get()))
            .repeat()
            .takeUntil(item -> {
              offset.set(item.getOffset() + item.getCount());
              return !item.hasMore();
            })
            .flatMap(item -> Observable.fromIterable(item.getItems()))
            .map(this::organizationResourceToDatabase)
            .observeOn(AndroidSchedulers.mainThread());
    return output;
  }

  @NotNull
  public Organization organizationResourceToDatabase(@NotNull OrganizationResource organizationResource){
    Organization organization = new Organization();
    organization.setItemIdentifier(DataTypeConverters.fromStringToUUID(organizationResource.getItemIdentifier()));
    organization.setItemTypeName(organizationResource.getItemTypeName());
    organization.setItemUpdateTimestamp(ZonedDateTime.parse(organizationResource.getItemUpdateTimestamp()
            ,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSVV")));
    organization.setItemNotesText(organizationResource.getItemNotesText());
    organization.setPartyName(organizationResource.getPartyName());
    organization.setPartyAlternativeName(organizationResource.getPartyAlternativeName());
    organization.setOrganizationWebDomain(organizationResource.getOrganizationWebDomain());

    insertParty(organization);

    return organization;

  }
  @NotNull
  public Person personResourceToDatabase(@NotNull PersonResource personResource){
    Person person = new Person();
    person.setItemIdentifier(DataTypeConverters.fromStringToUUID(personResource.getItemIdentifier()));
    person.setItemTypeName(personResource.getItemTypeName());
    person.setItemUpdateTimestamp(ZonedDateTime.parse(personResource.getItemUpdateTimestamp()
            ,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSVV")));
    person.setItemNotesText(personResource.getItemNotesText());
    person.setPersonFamilyName(personResource.getPersonFamilyName());
    person.setPersonGivenName(personResource.getPersonGivenName());
    person.setPersonEmailAddress(personResource.getPersonEmailAddress());

    insertParty(person);

    return person;
  }

  @NotNull
  public Category categoryResourceToDatabase(@NotNull CategoryResource categoryResource){
    Category category;
    switch (categoryResource.getItemTypeName()) {
      case "Tag":  //TODO Consider how best to implement this, for consistency and refactoring
        category = new Tag();
        break;
      case "Priority":
        category = new Priority();
        break;
      default:
        throw new IllegalStateException("Unexpected itemType value: " + categoryResource.getItemTypeName());
    }
    category.setItemIdentifier(DataTypeConverters.fromStringToUUID(categoryResource.getItemIdentifier()));
    category.setItemTypeName(categoryResource.getItemTypeName());
    category.setItemUpdateTimestamp(ZonedDateTime.parse(categoryResource.getItemUpdateTimestamp()
            ,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSVV")));
    category.setItemNotesText(categoryResource.getItemNotesText());
    category.setCategoryName(categoryResource.getCategoryName());
    category.setCategoryDefinitionText(categoryResource.getCategoryDefinitionText());
    switch(category.getClass().getSimpleName()) {
      case Priority.CLASS_SIMPLE_NAME:
        insertPriority((Priority) category);
        break;
      case Tag.CLASS_SIMPLE_NAME:
        insertTag((Tag) category);
        break;
      default:
        throw new IllegalStateException("Unexpected class: " + category.getClass().getSimpleName());
    }
    return category;
  }

  @NotNull
  public Transition transitionResourceToDatabase(@NotNull TransitionResource transitionResource) {
    Log.d(TAG,"Loading Transition = '" + transitionResource.toString() + "'");
    Transition transition = new Transition();
    transition.setItemIdentifier(DataTypeConverters.fromStringToUUID(transitionResource.getItemIdentifier()));
    transition.setItemTypeName(transitionResource.getItemTypeName());
    transition.setItemUpdateTimestamp(ZonedDateTime.parse(transitionResource.getItemUpdateTimestamp()
            ,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSVV")));
    transition.setItemNotesText(transitionResource.getItemNotesText());
    transition.setTransitionItemTypeName(transitionResource.getTransitionItemTypeName());
    transition.setTransitionFromStateName(transitionResource.getTransitionFromStateName());
    transition.setTransitionFromStateOrdinal(transitionResource.getTransitionFromStateOrdinal());
    transition.setTransitionToStateName(transitionResource.getTransitionToStateName());
    transition.setTransitionToStateOrdinal(transitionResource.getTransitionToStateOrdinal());
    transition.setTransitionReasonName(transitionResource.getTransitionReasonName());
    transition.setTransitionReasonOrdinal(transitionResource.getTransitionReasonOrdinal());
    transition.setTransitionDefinitionText(transitionResource.getTransitionDefinitionText());
    insertTransition(transition);
    Log.d(TAG,"Transition '" + transition.toString() + "' loaded");
    return transition;
  }

  @NotNull
  public Labor laborResourceToDatabase(@NotNull LaborResource laborResource) {
    Log.d(TAG,"Loading labor = '" + laborResource.getLaborName() + "'" );
    Labor labor = new Labor();
    labor.setItemIdentifier(DataTypeConverters.fromStringToUUID(laborResource.getItemIdentifier()));
    labor.setItemTypeName(laborResource.getItemTypeName());
    labor.setItemUpdateTimestamp(ZonedDateTime.parse(laborResource.getItemUpdateTimestamp(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSVV")));
    labor.setItemNotesText(laborResource.getItemNotesText());
    labor.setLaborName(laborResource.getLaborName());
    labor.setLaborIsChargeableFlag(laborResource.isLaborIsChargeableFlag());
    labor.setLaborIncludeInWork(laborResource.isLaborIncludeInWork());
    labor.setLaborChargeToIdentifier(DataTypeConverters.fromStringToUUID(laborResource.getLaborChargeToIdentifier()));
    labor.setParentLaborIdentifier(DataTypeConverters.fromStringToUUID(laborResource.getParentLaborIdentifier()));
    insertLabor(labor);
    Log.d(TAG,"Labor '" + labor.getLaborName() + "' loaded.");
    return labor;
  }

  @NotNull
  public Location locationResourceToDatabase(@NotNull LocationResource locationResource) {
    Log.d(TAG,"Loading location = '" + locationResource.getLocationName() + "'");
    Location location = new Location();
    location.setItemIdentifier(DataTypeConverters.fromStringToUUID(locationResource.getItemIdentifier()));
    location.setItemTypeName(locationResource.getItemTypeName());
    location.setItemUpdateTimestamp(ZonedDateTime.parse(locationResource.getItemUpdateTimestamp(),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssVV")));
    location.setItemNotesText(locationResource.getItemNotesText());
    location.setLocationName(locationResource.getLocationName());
    location.setLocationAbbreviationCode(locationResource.getLocationAbbreviationCode());
    location.setLocationParentIdentifier(DataTypeConverters.fromStringToUUID(locationResource.getParentLocationIdentifier()));
    insertLocation(location);
    Log.d(TAG,"Location '" + location.getLocationName() + "' loaded");
    return location;
  }

  @NotNull
  public ItemType itemTypeResourceToDatabase(@NotNull ItemTypeResource itemTypeResource) {
    ItemType itemType = new ItemType();
    itemType.setItemTypeName(itemTypeResource.getItemTypeName());
    itemType.setItemTypeAbbreviationCode(itemTypeResource.getItemTypeAbbreviationCode());
    itemType.setItemTypeDescription(itemTypeResource.getItemTypeDescription());
    itemType.setItemTypeSuperTypeName(itemTypeResource.getItemTypeSuperTypeName());
    insertItemType(itemType);
    return itemType;
  }
  // endregion
}