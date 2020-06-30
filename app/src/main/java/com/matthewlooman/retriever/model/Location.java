package com.matthewlooman.retriever.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(tableName="Location"
        ,indices={@Index(value="location_name",unique=true)
                 ,@Index(value="location_abbreviation_code",unique=true)
                 }
       )
public class Location extends Item {
  @ColumnInfo(name="location_name")
  String locationName;

  @ColumnInfo(name="location_abbreviation_code")
  String locationAbbreviationCode;

  @ColumnInfo(name="location_parent_identifier")
  UUID locationParentIdentifier;

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

  @Override
  public String toString(){ return this.locationName;}
}
