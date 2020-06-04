package com.matthewlooman.retriever.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.matthewlooman.retriever.model.ItemType;
import com.matthewlooman.retriever.model.Location;

import javax.inject.Singleton;

@Database(entities = {ItemType.class, Location.class}
, version=2
)
@TypeConverters({DataTypeConverters.class})
public abstract class RetrieverDatabase extends RoomDatabase {
  // --- SINGLETON ---
  private static volatile RetrieverDatabase INSTANCE;

  public abstract ItemTypeDao itemTypeDao();

  public abstract LocationDao locationDao();

  //TODO add migration from 1 to 2

}
