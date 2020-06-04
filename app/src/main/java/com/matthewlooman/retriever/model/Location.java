package com.matthewlooman.retriever.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(tableName="Location")
public class Location extends Item {
  @PrimaryKey
  @NonNull
  @ColumnInfo(name="item_identifier")
  UUID itemIdentifier;

  @ColumnInfo(name="item_type_name")
  String itemTypeName;

  @ColumnInfo(name="item_update_timestamp")
  LocalDateTime itemUpdateTimestamp;

  @ColumnInfo(name="item_notes")
  String itemNotes;

  @ColumnInfo(name="location_name")
  String locationName;

  @ColumnInfo(name="location_abbreviation_code")
  String locationAbbreviationCode;

  @ColumnInfo(name="location_parent_identifier")
  UUID locationParentIdentifier;

  @Override
  @NonNull
  public UUID getItemIdentifier() {
    return itemIdentifier;
  }

  @Override
  public void setItemIdentifier(@NonNull UUID itemIdentifier) {
    this.itemIdentifier = itemIdentifier;
  }

  @Override
  public String getItemTypeName() {
    return itemTypeName;
  }

  @Override
  public void setItemTypeName(String itemTypeName) {
    this.itemTypeName = itemTypeName;
  }

  @Override
  public LocalDateTime getItemUpdateTimestamp() {
    return itemUpdateTimestamp;
  }

  @Override
  public void setItemUpdateTimestamp(LocalDateTime itemUpdateTimestamp) {
    this.itemUpdateTimestamp = itemUpdateTimestamp;
  }

  @Override
  public String getItemNotes() {
    return itemNotes;
  }

  @Override
  public void setItemNotes(String itemNotes) {
    this.itemNotes = itemNotes;
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

  public UUID getLocationParentIdentifier() {
    return locationParentIdentifier;
  }

  public void setLocationParentIdentifier(UUID locationParentIdentifier) {
    this.locationParentIdentifier = locationParentIdentifier;
  }
}
