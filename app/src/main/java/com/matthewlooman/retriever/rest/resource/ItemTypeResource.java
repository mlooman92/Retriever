package com.matthewlooman.retriever.rest.resource;

import com.google.gson.annotations.SerializedName;

public class ItemTypeResource {


  @SerializedName("item_type_name")
  private String itemTypeName;

  @SerializedName("item_type_abbrev_cd")
  private String itemTypeAbbreviationCode;

  @SerializedName("item_type_def_text")
  private String itemTypeDescription;

  @SerializedName("item_type_supertype_name")
  private String itemTypeSuperTypeName;

  @SerializedName("item_type_tree_string")
  private String itemTypeTreeString;

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

  public String getItemTypeTreeString() {
    return itemTypeTreeString;
  }

  public void setItemTypeTreeString(String itemTypeTreeString) {
    this.itemTypeTreeString = itemTypeTreeString;
  }
}
