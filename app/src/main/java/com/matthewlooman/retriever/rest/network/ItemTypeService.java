package com.matthewlooman.retriever.rest.network;

import com.matthewlooman.retriever.rest.resource.Item;
import com.matthewlooman.retriever.rest.resource.ItemTypeResource;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ItemTypeService {
  @GET("/pls/apex/sc/log/itemtype")
  Single<Item<ItemTypeResource>> loadAll();

}
