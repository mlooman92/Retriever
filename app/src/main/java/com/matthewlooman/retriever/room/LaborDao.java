package com.matthewlooman.retriever.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.matthewlooman.retriever.model.Labor;

import java.util.List;
import java.util.UUID;

@Dao
public interface LaborDao {
  @Query("SELECT * FROM Labor")
  List<Labor> getLabors();

  @Query("SELECT * FROM Labor WHERE item_identifier = :itemIdentifier")
  Labor getLabor(UUID itemIdentifier);

  @Query("SELECT * FROM Labor WHERE labor_name = :laborName")
  Labor getLabor(String laborName);

  @Insert(onConflict= OnConflictStrategy.REPLACE)
  void save(Labor labor);

  @Delete
  void delete(Labor labor);
}
