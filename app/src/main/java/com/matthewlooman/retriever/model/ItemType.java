package com.matthewlooman.retriever.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Item_Type")
public class ItemType {

  @PrimaryKey
  @ColumnInfo(name="item_type_name")
  private String itemTypeName;

  @ColumnInfo(name="item_type_abbreviation_code")
  private String itemTypeAbbreviationCode;

  @ColumnInfo(name="item_type_description")
  private String itemTypeDescription;

  @ColumnInfo(name="item_type_supertype_name")
  private String itemTypeSuperTypeName;

  public String getItemTypeName(){
    return itemTypeName;
  }
  public void setItemTypeName(String itemTypeName){
    this.itemTypeName = itemTypeName;
  }

  public String getItemTypeAbbreviationCode() {
    return itemTypeAbbreviationCode;
  }

  public void setItemTypeAbbreviationCode(String itemTypeAbbreviationCode) {
    this.itemTypeAbbreviationCode = itemTypeAbbreviationCode;
  }

  public String getItemTypeDescription() {
    return itemTypeDescription;
  }

  public void setItemTypeDescription(String itemTypeDescription) {
    this.itemTypeDescription = itemTypeDescription;
  }

  public String getItemTypeSuperTypeName() {
    return itemTypeSuperTypeName;
  }

  public void setItemTypeSuperTypeName(String itemTypeSuperTypeName) {
    this.itemTypeSuperTypeName = itemTypeSuperTypeName;
  }
}
