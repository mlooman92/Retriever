package com.matthewlooman.retriever.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.matthewlooman.retriever.model.ItemType;
import com.matthewlooman.retriever.model.Location;

import java.util.List;
import java.util.UUID;

@Dao
public interface LocationDao {

  @Insert(onConflict= OnConflictStrategy.REPLACE)
  void save(Location location);

  @Query("SELECT * FROM Location")
  List<Location> getLocations();

  @Query("SELECT * FROM Location WHERE item_identifier = :itemIdentifier")
  Location getLocation(UUID itemIdentifier);

  @Query("SELECT * FROM Location WHERE item_type_name = :locationName")
  Location getLocation(String locationName);

  @Delete
  void delete(Location location );

}
