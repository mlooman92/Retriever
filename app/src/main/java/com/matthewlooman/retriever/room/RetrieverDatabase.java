package com.matthewlooman.retriever.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.matthewlooman.retriever.model.ItemType;

import javax.inject.Singleton;

@Database(entities = {ItemType.class}
, version=1
)
public abstract class RetrieverDatabase extends RoomDatabase {
  // --- SINGLETON ---
  private static volatile RetrieverDatabase INSTANCE;

  public abstract ItemTypeDao itemTypeDao();

}
