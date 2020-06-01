package com.matthewlooman.retriever.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.matthewlooman.retriever.model.ItemType;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface ItemTypeDao {

  @Insert(onConflict= OnConflictStrategy.REPLACE)
  void save(ItemType itemType);

  @Query("SELECT * FROM Item_Type")
  List<ItemType> getItemTypes();

  @Query("SELECT * FROM Item_Type WHERE item_type_name = :itemTypeName")
  ItemType getItemType(String itemTypeName);

  @Delete
  void delete(ItemType itemType);


}
