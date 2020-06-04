package com.matthewlooman.retriever.rest.resource;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Date;

public class LocationResource {
  @SerializedName("item_identifier")
  String itemIdentifier;

  @SerializedName("item_type_name")
  String itemTypeName;

  @SerializedName("item_notes_text")
  String itemNotestText;

  @SerializedName("item_update_timestamp")
  String itemUpdateTimestamp;

  @SerializedName("location_name")
  String locationName;

  @SerializedName("location_abbreviation_code")
  String locationAbbreviationCode;

  @SerializedName("parent_location_identifier")
  String parentLocationIdentifier;

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
    return itemNotestText;
  }

  public void setItemNotestText(String itemNotestText) {
    this.itemNotestText = itemNotestText;
  }

  public String getItemUpdateTimestamp() {
    return itemUpdateTimestamp;
  }

  public void setItemUpdateTimestamp(String itemUpdateTimestamp) {
    this.itemUpdateTimestamp = itemUpdateTimestamp;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public String getLocationAbbreviationCode() {
    return locationAbbreviationCode;
  }

  public void setLocationAbbreviationCode(String locationAbbreviationCode) {
    this.locationAbbreviationCode = locationAbbreviationCode;
  }

  public String getParentLocationIdentifier() {
    return parentLocationIdentifier;
  }

  public void setParentLocationIdentifier(String parentLocationIdentifier) {
    this.parentLocationIdentifier = parentLocationIdentifier;
  }
}
