package com.matthewlooman.retriever.rest.network;

import com.matthewlooman.retriever.rest.resource.Item;
import com.matthewlooman.retriever.rest.resource.LocationResource;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationService {
  @GET("pls/apex/sc/log/location")
  Observable<Item<LocationResource>> loadLocations(@Query("offset") int offset);

}
