package com.matthewlooman.retriever.room;

import androidx.room.Dao;
import androidx.room.Query;

import com.matthewlooman.retriever.model.Organization;
import com.matthewlooman.retriever.model.Person;

import java.util.List;

@Dao
public interface PersonDao extends PartyDao {
  @Query("SELECT * FROM Person")
  List<Person> getPersons();

  @Query("SELECT * FROM Party WHERE party_uri_string = :emailAddress")
  Person getPersonByEmailAddress(String emailAddress);
}
