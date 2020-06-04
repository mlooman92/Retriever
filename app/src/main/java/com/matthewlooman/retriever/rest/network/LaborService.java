package com.matthewlooman.retriever.rest.network;

import com.matthewlooman.retriever.rest.resource.Item;
import com.matthewlooman.retriever.rest.resource.LaborResource;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LaborService {
  @GET("pls/apex/sc/log/labor")
  Observable<Item<LaborResource>> loadLabors(@Query("offset") int offset);
}
