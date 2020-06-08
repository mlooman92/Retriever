package com.matthewlooman.retriever.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "Tag")
public class Tag extends Category {
  public final static String CLASS_SIMPLE_NAME = "Tag";
// Implementation just to keep type separate from other Category Types.  No independent methods
}
