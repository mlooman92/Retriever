package com.matthewlooman.retriever.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class Party extends Item {
  @ColumnInfo(name="party_name") private String partyName;
  @ColumnInfo(name="party_alternative_name") private String partyAlternativeName;
  @ColumnInfo(name="party_uri_string") private String partyUriString;

  public String getPartyName() { return partyName; }
  public void setPartyName(String partyName) { this.partyName = partyName; }
  public String getPartyAlternativeName() { return partyAlternativeName; }
  public void setPartyAlternativeName(String partyAlternativeName) { this.partyAlternativeName = partyAlternativeName;  }
  public String getPartyUriString() {  return partyUriString; }
  public void setPartyUriString(String partyUriString) { this.partyUriString = partyUriString; }

  @Override
  public String toString() { return partyName; }
}
