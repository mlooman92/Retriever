package com.matthewlooman.retriever.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "Priority")
public class Priority extends Category {
  public static final String CLASS_SIMPLE_NAME = "Priority";
// Implementation just to keep type separate from other Category Types.  No independent methods
}
