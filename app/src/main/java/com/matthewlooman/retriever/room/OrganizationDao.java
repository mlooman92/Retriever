package com.matthewlooman.retriever.room;

import androidx.room.Dao;
import androidx.room.Query;

import com.matthewlooman.retriever.model.Organization;

import java.util.List;

@Dao
public interface OrganizationDao extends PartyDao {
  // Extending Party DAO for Organization
  @Query("SELECT * FROM Organization")
  List<Organization> getOrganizations();

  @Query("SELECT * FROM Organization WHERE party_name = :organizationName")
  Organization getOrganization(String organizationName);

  @Query("SELECT * FROM Organization WHERE party_alternative_name = :abbreviatedName")
  Organization getOrganizationByAbbreviation(String abbreviatedName);

  @Query("SELECT * FROM Organization WHERE party_uri_string = :webDomain")
  Organization getOrganizationByWebDomain(String webDomain);
}
