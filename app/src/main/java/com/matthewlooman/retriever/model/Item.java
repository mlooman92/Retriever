package com.matthewlooman.retriever.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

public abstract class Item {
  @PrimaryKey
  @NonNull
  @ColumnInfo(name="item_identifier") private UUID itemIdentifier;
  @ColumnInfo(name="item_type_name") private String itemTypeName;
  @ColumnInfo(name="item_update_timestamp") private ZonedDateTime itemUpdateTimestamp;
  @ColumnInfo(name="item_notes_text") private String itemNotesText;

  public UUID getItemIdentifier(){return itemIdentifier;};

  public void setItemIdentifier(UUID itemIdentifier){this.itemIdentifier = itemIdentifier;};
  public String getItemTypeName(){return itemTypeName;}
  public void setItemTypeName(String itemTypeName){this.itemTypeName = itemTypeName;}
  public ZonedDateTime getItemUpdateTimestamp(){return itemUpdateTimestamp;}
  public void setItemUpdateTimestamp(ZonedDateTime itemUpdateTimestamp){this.itemUpdateTimestamp = itemUpdateTimestamp;}
  public String getItemNotesText(){return itemNotesText;}
  public void setItemNotesText(String itemNotesText){this.itemNotesText = itemNotesText;}
}
