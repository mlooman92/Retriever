package com.matthewlooman.retriever.rest.network;

import com.matthewlooman.retriever.model.Tag;
import com.matthewlooman.retriever.rest.resource.Item;
import com.matthewlooman.retriever.rest.resource.LocationResource;
import com.matthewlooman.retriever.rest.resource.TagResource;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TagService {
  @GET("pls/apex/sc/log/tag")
  Observable<Item<TagResource>> loadTags(@Query("offset") int offset);
}
