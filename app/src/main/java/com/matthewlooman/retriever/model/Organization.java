package com.matthewlooman.retriever.model;

import androidx.room.Entity;
import androidx.room.Index;

import com.matthewlooman.retriever.model.Party;

@Entity(tableName="Organization"
        ,indices={@Index(value="party_name",unique=true)
                ,@Index(value="party_alternative_name",unique=true)
                ,@Index(value="party_uri_string",unique=true)
                }
       )
public class Organization extends Party {
  public String getOrganizationWebDomain(){return super.getPartyUriString();}
  public void setOrganizationWebDomain(String webDomain){super.setPartyUriString(webDomain);}
}
