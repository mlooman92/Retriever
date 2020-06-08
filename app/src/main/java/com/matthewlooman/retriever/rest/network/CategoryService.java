package com.matthewlooman.retriever.rest.network;

import com.matthewlooman.retriever.rest.resource.Item;
import com.matthewlooman.retriever.rest.resource.CategoryResource;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CategoryService {
  @GET("pls/apex/sc/log/{category_type}")
  Observable<Item<CategoryResource>> loadCategories(@Path("category_type") String category_type, @Query("offset") int offset);
}
