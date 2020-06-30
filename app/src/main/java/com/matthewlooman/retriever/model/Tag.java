package com.matthewlooman.retriever.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "Tag"
        ,indices={@Index(value="category_name",unique=true)}
       )
public class Tag extends Category {
  public final static String CLASS_SIMPLE_NAME = "Tag";
// Implementation just to keep type separate from other Category Types.  No independent methods
}
