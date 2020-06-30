package com.matthewlooman.retriever.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.matthewlooman.retriever.model.Party;

import java.util.List;
import java.util.UUID;

@Dao
public interface PartyDao {
  @Query("SELECT * FROM Party")
  List<Party> getParties();

  @Query("SELECT * FROM Party WHERE item_identifier = :itemIdentifier")
  Party getParty(UUID itemIdentifier);

  @Query("SELECT * FROM Party WHERE party_name = :partyName")
  Party getParty(String partyName);

  @Delete
  void delete(Party party);

  @Insert(onConflict=OnConflictStrategy.REPLACE)
  void save(Party party);
}
