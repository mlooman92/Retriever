package com.matthewlooman.retriever.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

import java.util.UUID;

@Entity(tableName="Person"
        ,indices={@Index(value="party_name",unique=true)
        ,@Index(value="party_alternative_name",unique=true)
        ,@Index(value="party_uri_string",unique=true)
}
)
public class Person extends Party {
  @ColumnInfo(name="person_given_name") String personGivenName;
  @ColumnInfo(name="person_family_name") String personFamilyName;
  @ColumnInfo(name="person_manager_identifier") UUID personManagerIdentifier;

  public String getPersonGivenName() { return personGivenName; }

  public void setPersonGivenName(String personGivenName) {
    this.personGivenName = personGivenName;
    super.setPartyName(personGivenName + " " + personFamilyName);
    super.setPartyAlternativeName(personFamilyName + ", " + personGivenName);
  }

  public String getPersonFamilyName() { return personFamilyName;}

  public void setPersonFamilyName(String personFamilyName) {
    this.personFamilyName = personFamilyName;
    super.setPartyName(personGivenName + " " + personFamilyName);
    super.setPartyAlternativeName(personFamilyName + ", " + personGivenName);
  }
  public UUID getPersonManagerIdentifier() {return personManagerIdentifier; }
  public void setPersonManagerIdentifier(UUID personManagerIdentifier) {this.personManagerIdentifier = personManagerIdentifier; }

  @Override
  public void setPartyName(String partyName) {
    super.setPartyName(partyName);
    String[] personName = partyName.split(" ");
    personGivenName = personName[0].trim();
    for(int i=1;i<personName.length-1;i++){
      personGivenName = personGivenName + " " + personName[i].trim();
    }
    if(personName.length>1){
      this.personFamilyName = personName[personName.length-1];
    }
    super.setPartyAlternativeName(this.personFamilyName + ", " + this.personGivenName);

  }

  @Override
  public void setPartyAlternativeName(String partyAlternativeName) {
    super.setPartyAlternativeName(partyAlternativeName);
    String[] personName = partyAlternativeName.split(",");
    personFamilyName = personName[0].trim();
    if(personName.length>1){
      this.personGivenName = personName[1].trim();
      for(int i=2;i<personName.length;i++){
        this.personGivenName = personGivenName + "," + personName[i].trim();
      }
    }
    super.setPartyName(this.personGivenName + " " + this.personFamilyName);
  }

  public void setPersonEmailAddress(String personEmailAddress){
    // TODO Add validation that this is a valid email address
    super.setPartyUriString(personEmailAddress);
  }
}
