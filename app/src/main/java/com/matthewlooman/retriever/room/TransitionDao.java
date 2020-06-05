package com.matthewlooman.retriever.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.matthewlooman.retriever.model.Transition;

import java.util.List;
import java.util.UUID;

@Dao
public interface TransitionDao {
  @Query("SELECT * FROM Transition")
  List<Transition> getTransitions();

  @Query("SELECT * FROM Transition WHERE item_identifier = :itemIdentifier")
  Transition getTransition(UUID itemIdentifier);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void save(Transition transition);

  @Delete
  void delete(Transition transition);
}
