package com.matthewlooman.retriever.rest.network;

import com.matthewlooman.retriever.rest.resource.Item;
import com.matthewlooman.retriever.rest.resource.OrganizationResource;
import com.matthewlooman.retriever.rest.resource.PersonResource;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PartyService {
  @GET("pls/apex/sc/log/person")
  Observable<Item<PersonResource>> loadPersons(@Query("offset") int offset);

  @GET("pls/apex/sc/log/organization")
  Observable<Item<OrganizationResource>> loadOrganizations(@Query("offset") int offset);
}
