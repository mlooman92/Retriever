package com.matthewlooman.retriever.rest.resource;

import com.google.gson.annotations.SerializedName;

public class PersonResource {
  @SerializedName("item_identifier") String itemIdentifier;
  @SerializedName("item_type_name") String itemTypeName;
  @SerializedName("item_notes_text") String itemNotesText;
  @SerializedName("item_update_timestamp") String itemUpdateTimestamp;
  @SerializedName("person_email_address") String personEmailAddress;
  @SerializedName("person_family_name") String personFamilyName;
  @SerializedName("person_given_name") String personGivenName;
  @SerializedName("person_manager_identifier") String personManagerIdentifier;

  public String getItemIdentifier() {
    return itemIdentifier;
  }

  public void setItemIdentifier(String itemIdentifier) {
    this.itemIdentifier = itemIdentifier;
  }

  public String getItemTypeName() {
    return itemTypeName;
  }

  public void setItemTypeName(String itemTypeName) {
    this.itemTypeName = itemTypeName;
  }

  public String getItemNotesText() {
    return itemNotesText;
  }

  public void setItemNotesText(String itemNotesText) {
    this.itemNotesText = itemNotesText;
  }

  public String getItemUpdateTimestamp() {
    return itemUpdateTimestamp;
  }

  public void setItemUpdateTimestamp(String itemUpdateTimestamp) {
    this.itemUpdateTimestamp = itemUpdateTimestamp;
  }

  public String getPersonEmailAddress() {
    return personEmailAddress;
  }

  public void setPersonEmailAddress(String personEmailAddress) {
    this.personEmailAddress = personEmailAddress;
  }

  public String getPersonFamilyName() {
    return personFamilyName;
  }

  public void setPersonFamilyName(String personFamilyName) {
    this.personFamilyName = personFamilyName;
  }

  public String getPersonGivenName() {
    return personGivenName;
  }

  public void setPersonGivenName(String personGivenName) {
    this.personGivenName = personGivenName;
  }

  public String getPersonManagerIdentifier() {
    return personManagerIdentifier;
  }

  public void setPersonManagerIdentifier(String personManagerIdentifier) {
    this.personManagerIdentifier = personManagerIdentifier;
  }
}
