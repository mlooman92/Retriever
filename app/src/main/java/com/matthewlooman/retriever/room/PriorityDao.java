package com.matthewlooman.retriever.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.matthewlooman.retriever.model.Priority;

import java.util.List;
import java.util.UUID;

@Dao
public interface PriorityDao {
  @Query("Select * FROM Priority")
  List<Priority> getPriorities();

  @Query("SELECT * FROM Priority WHERE category_name = :categoryName")
  Priority getPriority(String categoryName);

  @Query("SELECT * FROM Priority WHERE item_identifier = :itemIdentifier")
  Priority getPriority(UUID itemIdentifier);

  @Insert
  void save(Priority priority);

  @Delete
  void delete(Priority priority);
}
