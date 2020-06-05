package com.matthewlooman.retriever.rest.network;

import com.matthewlooman.retriever.rest.resource.Item;
import com.matthewlooman.retriever.rest.resource.TransitionResource;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TransitionService {
  @GET("pls/apex/sc/log/transition")
  Observable<Item<TransitionResource>> loadTransitions(@Query("offset") int offset);
}
