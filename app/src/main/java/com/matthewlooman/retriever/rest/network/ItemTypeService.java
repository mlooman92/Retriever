package com.matthewlooman.retriever.rest.network;

import com.matthewlooman.retriever.rest.resource.Item;
import com.matthewlooman.retriever.rest.resource.ItemTypeResource;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItemTypeService {
  @GET("/pls/apex/sc/log/itemtype")
  Observable<Item<ItemTypeResource>> loadItemTypes();

  @GET("/pls/apex/sc/log/itemtype")
  Observable<Item<ItemTypeResource>> loadItemTypes(@Query("offset") int offset);

}
