package com.matthewlooman.retriever.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.matthewlooman.retriever.model.ItemType;
import com.matthewlooman.retriever.model.Labor;
import com.matthewlooman.retriever.model.Location;

import javax.inject.Singleton;

@Database(entities = {ItemType.class, Location.class, Labor.class}
, version=3
)
@TypeConverters({DataTypeConverters.class})
public abstract class RetrieverDatabase extends RoomDatabase {
  // --- SINGLETON ---
  private static volatile RetrieverDatabase INSTANCE;

  public abstract ItemTypeDao itemTypeDao();

  public abstract LocationDao locationDao();

  public abstract LaborDao laborDao();

}
