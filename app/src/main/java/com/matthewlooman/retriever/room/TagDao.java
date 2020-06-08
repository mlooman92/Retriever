package com.matthewlooman.retriever.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.matthewlooman.retriever.model.Tag;

import java.util.List;
import java.util.UUID;

@Dao
public interface TagDao {
  @Query("Select * FROM Tag")
  List<Tag> getTags();

  @Query("SELECT * FROM Tag WHERE category_name = :categoryName")
  Tag getTag(String categoryName);

  @Query("SELECT * FROM Tag WHERE item_identifier = :itemIdentifier")
  Tag getTag(UUID itemIdentifier);

  @Insert(onConflict= OnConflictStrategy.REPLACE)
  void save(Tag tag);

  @Delete
  void delete(Tag tag);
}
